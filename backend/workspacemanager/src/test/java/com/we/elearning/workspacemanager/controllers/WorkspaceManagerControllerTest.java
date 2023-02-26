package com.we.elearning.workspacemanager.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WorkspaceManagerController.class)
class WorkspaceManagerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    WorkspaceManagerController workspaceManagerController;

    @Test
    void createWorkspace() {
    }

    @Test
    void deleteWorkspace() {
    }

    @Test
    void getAllWorkspaces() {
    }
}