/**
 * 
 */
package com.coderlook.tgs.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.coderlook.tgs.constants.TgsConstants;
import com.coderlook.tgs.dto.SstAccount;
import com.coderlook.tgs.dto.SstUserAccount;
import com.coderlook.tgs.service.impl.SstAccountServiceImpl;
import com.coderlook.tgs.service.impl.SstUserAccountServiceImpl;

/**
 * 
 * @author Gouranga
 *
 */
@Service
public class UserAuthenticationService implements AuthenticationProvider {

	@Autowired
	SstAccountServiceImpl sstAccountService;

	@Autowired
	SstUserAccountServiceImpl sstUserAccountService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		Authentication retVal = null;
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

		if (auth != null) {
			String name = auth.getName();
			String password = auth.getCredentials().toString();
			System.out.println(name);
			System.out.println(password);
			
			SstAccount account = sstAccountService.fetchSstAccountByProperty(name);

			if (account != null && account.getLocked().equals("0") && BCrypt.checkpw(password, account.getPassword())) {
				grantedAuths.add(new SimpleGrantedAuthority(TgsConstants.ROLE_USER));

				switch (account.getUserType().getId()) {
				case TgsConstants.ID_ADMIN:
					grantedAuths.add(new SimpleGrantedAuthority(TgsConstants.ROLE_ADMIN));
					break;
				case TgsConstants.ID_BM:
					grantedAuths.add(new SimpleGrantedAuthority(TgsConstants.ROLE_BM));
					break;
				case TgsConstants.ID_ASM:
					grantedAuths.add(new SimpleGrantedAuthority(TgsConstants.ROLE_ASM));
					break;
				case TgsConstants.ID_SO:
					grantedAuths.add(new SimpleGrantedAuthority(TgsConstants.ROLE_SO));
					break;
				default:
					// do nothing
					break;
				}
				System.out.println(grantedAuths.toString());
				retVal = new UsernamePasswordAuthenticationToken(name, "", grantedAuths);

				ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
				HttpSession session = attr.getRequest().getSession(false);
				SstUserAccount userAccount = sstUserAccountService.fetchActiveSstUserAccountByLoginId(name);
				session.setAttribute("loggedInUser", userAccount.getSstUser());
				session.setAttribute("loggedInAccount", userAccount.getSstAccount());

			} else if (account != null && account.getLocked().equals("1")) {
				System.out.println("Account is locked! Please contact your system administrator!");
				throw new AuthenticationCredentialsNotFoundException("Account is locked! Please contact your system administrator!");
			} else if (account == null || account.getLoginId() == null) {

			} else {
				System.out.println("Login Id or Password is invalid!");
				throw new BadCredentialsException("Login Id or Password is invalid!");
			}
		} else {
			System.out.println("Server Error! Please contact your system administrator!");
			throw new AuthenticationCredentialsNotFoundException("Server Error! Please contact your system administrator!");
		}

		return retVal;
	}

	@Override
	public boolean supports(Class<?> tokenType) {
		return tokenType.equals(UsernamePasswordAuthenticationToken.class);
	}
}
