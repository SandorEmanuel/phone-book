package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.domain.Contact;
import org.fasttrackit.service.ContactService;
import org.fasttrackit.transfer.SaveContactRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (urlPatterns = "/contacts")

public class ContactServlet extends HttpServlet {

    private ContactService contactService = new ContactService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        ObjectMapper objectMapper = new ObjectMapper();
        SaveContactRequest saveContactRequest = objectMapper.readValue(req.getReader(), SaveContactRequest.class);

        try {
            contactService.createContact(saveContactRequest);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        try {
            List<Contact> contacts = contactService.getContacts();


            ObjectMapper objectMapper = new ObjectMapper();
            String responseJson = objectMapper.writeValueAsString(contacts);


            resp.setContentType("application/json");
            resp.getWriter().print(responseJson);
            resp.getWriter().flush();

        } catch (Exception e) {
            resp.sendError(500, "There was an error processing your request. " +e.getMessage());

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            setAccessControlHeaders(resp);

            ObjectMapper objectMapper = new ObjectMapper();
            SaveContactRequest saveContactRequest = objectMapper.readValue(req.getReader(), SaveContactRequest.class);

            try {
                contactService.updateContact(saveContactRequest);
            } catch (Exception e) {
                resp.sendError(500, "Internal error: " + e.getMessage());
            }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        ObjectMapper objectMapper = new ObjectMapper();
        SaveContactRequest saveContactRequest = objectMapper.readValue(req.getReader(), SaveContactRequest.class);

        try {
            contactService.deleteContact(saveContactRequest);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
