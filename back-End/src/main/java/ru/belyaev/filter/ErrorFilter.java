//package ru.belyaev.filter;
//
//import ru.belyaev.exception.InternalServerErrorException;
//import ru.belyaev.exception.ResourceNotFoundException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//import java.io.IOException;
//
//@WebFilter(filterName = "MyFilter", urlPatterns = "/*")
//public class ErrorFilter extends HttpFilter {
//
//    private static final String INTERNAL_ERROR = "Internal error";
//
//    @Override
//    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
//        try {
//            chain.doFilter(req, new ThrowExceptionInsteadOfSendErrorResponse(resp));
//        } catch (Throwable th) {
//            handleException(req, resp);
//        }
//    }
//
//    private void handleException(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        int statusCode = resp.getStatus();
//        resp.setStatus(statusCode);
//        req.setAttribute("status", statusCode);
//        req.getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(req,resp);
//    }
//
//    private static class ThrowExceptionInsteadOfSendErrorResponse extends HttpServletResponseWrapper {
//        public ThrowExceptionInsteadOfSendErrorResponse(HttpServletResponse response) {
//            super(response);
//        }
//
//        @Override
//        public void sendError(int sc) {
//            sendError(sc, INTERNAL_ERROR);
//        }
//
//        @Override
//        public void sendError(int sc, String msg) {
//            switch (sc) {
//                case 404: {
//                    throw new ResourceNotFoundException(msg);
//                }
//                default:
//                    throw new InternalServerErrorException(msg);
//            }
//        }
//    }
//}
