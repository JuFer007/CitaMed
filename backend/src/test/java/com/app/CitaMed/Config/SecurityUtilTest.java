package com.app.CitaMed.Config;

import com.app.CitaMed.Util.SecurityUtil;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collection;
import java.util.List;

class SecurityUtilTest {

    private MockedStatic<SecurityContextHolder> securityContextHolderMock;
    private SecurityContext securityContext;
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        securityContextHolderMock = Mockito.mockStatic(SecurityContextHolder.class);
        securityContext = Mockito.mock(SecurityContext.class);
        authentication = Mockito.mock(Authentication.class);
        securityContextHolderMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);
    }

    @AfterEach
    void tearDown() {
        securityContextHolderMock.close();
    }

    @Test
    void getCurrentUsername_deberiaRetornarUsername_CuandoUsuarioAutenticado() {
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.isAuthenticated()).thenReturn(true);
        Mockito.when(authentication.getName()).thenReturn("jperez");

        assertEquals("jperez", SecurityUtil.getCurrentUsername());
    }

    @Test
    void getCurrentUsername_deberiaRetornarNull_CuandoNoHayAutenticacion() {
        Mockito.when(securityContext.getAuthentication()).thenReturn(null);

        assertNull(SecurityUtil.getCurrentUsername());
    }

    @Test
    void getCurrentUsername_deberiaRetornarNull_CuandoEsAnonymousUser() {
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.isAuthenticated()).thenReturn(true);
        Mockito.when(authentication.getName()).thenReturn("anonymousUser");

        assertNull(SecurityUtil.getCurrentUsername());
    }

    @Test
    void hasRole_deberiaRetornarTrue_CuandoUsuarioTieneElRol() {
        Collection<? extends GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN")
        );
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getAuthorities()).thenReturn((Collection) authorities);

        assertTrue(SecurityUtil.hasRole("ADMIN"));
    }

    @Test
    void hasRole_deberiaRetornarFalse_CuandoUsuarioNoTieneElRol() {
        Collection<? extends GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_MEDICO")
        );
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getAuthorities()).thenReturn((Collection) authorities);

        assertFalse(SecurityUtil.hasRole("ADMIN"));
    }

    @Test
    void hasRole_deberiaRetornarFalse_CuandoNoHayAutenticacion() {
        Mockito.when(securityContext.getAuthentication()).thenReturn(null);

        assertFalse(SecurityUtil.hasRole("ADMIN"));
    }

    @Test
    void isAdmin_deberiaRetornarTrue_CuandoUsuarioEsAdmin() {
        Collection<? extends GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN")
        );
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getAuthorities()).thenReturn((Collection) authorities);

        assertTrue(SecurityUtil.isAdmin());
    }

    @Test
    void isMedico_deberiaRetornarTrue_CuandoUsuarioEsMedico() {
        Collection<? extends GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_MEDICO")
        );
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getAuthorities()).thenReturn((Collection) authorities);

        assertTrue(SecurityUtil.isMedico());
    }

    @Test
    void isRecepcionista_deberiaRetornarTrue_CuandoUsuarioEsRecepcionista() {
        Collection<? extends GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_RECEPCIONISTA")
        );
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getAuthorities()).thenReturn((Collection) authorities);

        assertTrue(SecurityUtil.isRecepcionista());
    }
}
