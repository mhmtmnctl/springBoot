package com.tpro.security;

import java.security.AuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration//konfigurasyon yapacağımı söylüyorum. bu class config class'ı
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//security'i metod bazlı olarak çalışmak istediğimiz için koyduk, yazmasaydık metod bazlı çalışamazdık
/*
 * --------Spring Security--------
sen kimsin? -->authentication
acaba burda bulunmaya yetkim var mı?-->authorization
23:13
***login ile giriş yaptığımızda bu authentication oluyor.
ismi emin şifreside bu...
***lms'se girdik mesela ama heryere girmeye yetkimiz yok. bir ders videosu ekleyemeyiz. buna yetkimiz yok. ya admin ya teacher olarak girmemiz lazım
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;//security giriş katmanı servis katmanı old. için önce bunu enjekte ettik
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	http.csrf().//api ürettiğimiz için crsf'e gerek kalmıyor çünkü sadece get yaptırıyor.put vs yaptırmıyor  	
	disable().//default olarak enabled biz disable yaptık
	authorizeHttpRequests().//önce bi authorized et
	antMatchers("/","index.html","/css/*","/buraya istediğimiz end pointi yazabiliriz").//bu endpointlerle gelirsen girebilirsin auth gerek yok
	permitAll().//bunları muaf tut(yukardaki endpointleri), yani security uygulama
	anyRequest().//ama bunların dışında ne gelirse gelsin 
	authenticated().//auth etceksin.
	and().httpBasic();    // ve bunları basic auth ile yap..artık her requeste kullanıcı adı ve şifresini vermesi lazım ve decode etmesi lazım.		
	}
	/*
	//inMemory olarak userları oluşturuyoruz. program kapandığında gidecek
	
	@Override
	@Bean//aşağıdaki metodu biz yazmadık dışardan aldık o yüzcden ioc'ye atmamız lazım
	protected UserDetailsService userDetailsService() {//userleri belli bir formatta oluşturcaz
		
		UserDetails userEmin = User.builder().//User--> spring security core 'dan geliyor
				username("emin").
				password(passwordEncoder().encode("emin")).//encode etmemiz lazım onu da aşağıdaki metodda yaptık
				roles("ADMIN").
				build();	
		
		UserDetails userAlvia = User.builder().username("alvia").password(passwordEncoder().encode("alvia")).roles("STUDENT").build();//2.user oluşturduk
		UserDetails userIbrahim = User.builder().username("ibrahim").password(passwordEncoder().encode("ibrahim")).roles("STUDENT","ADMIN").build();//birden fazla yetki verebiliyorruz.
		
		return new InMemoryUserDetailsManager(new UserDetails[] {userEmin,userAlvia,userIbrahim});//InMemoryUserDetailsManager metodu üzerinden UserDetailsleri giriyoruz
		
		
	}
	*/ //yoruma aldık inMemory kısmını
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {//görseldeki yeşil kısım...authentication manager yani
		
		auth.authenticationProvider(authProvider());//aşağıda bu metodu oluşturduk. içerdeki authProvider'ı
		
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		//kullanıcı bilgilerini girelim
		authProvider.setUserDetailsService(userDetailsService);
		//kullanılacak encoder-decoder metodunu belitrliyoruz
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
		
	}
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {//resimdeki pembe kısım burası oluyor. yani password encoder
		
		return new BCryptPasswordEncoder(10);//neden 10 kere dedik kaçırdım burayı sor.
	}
}

/*
 * ilk requesti authentication filter karşılıyor.
 * securitycontext te login olan kullanıcı bilgileri tuuluyor
 * 
 */









