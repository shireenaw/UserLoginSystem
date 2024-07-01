package com.premiergaming.springsecurity;

import com.premiergaming.controller.AdminController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringSecurityTestConfig.class)    //Passing the security config class details.
@AutoConfigureMockMvc
class SpringSecurityApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired @InjectMocks
    private AdminController adminController;

    @MockBean
    private SecurityContext securityContext;

    @Test
    void contextLoads() {

        assertNotNull(adminController);
    }

    // Testing if the user with ROLE_ADMIN is able to access the admin api.
    @Test
    @WithUserDetails("admin@mail.com")
    public void adminTestSuccess() throws Exception{
        mockMvc.perform(get("/admin/createUser"))
                .andExpect(status().isOk());
    }

    //Testing fail case where user having ROLE_USER should be denied access.
    @Test
    @WithUserDetails("user@mail.com")
    public void adminTestFail() throws Exception{
        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isForbidden());
    }

}