package com.jobportal;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class JobPortalServer {

    public static void main(String[] args) throws IOException {

        HttpServer server =
                HttpServer.create(new InetSocketAddress(8080), 0);


        // ==========================================
        // REGISTER API
        // ==========================================

        server.createContext("/api/register", exchange -> {

            String response;

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                String body = new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );

                String[] data = body.split("&");

                String name = URLDecoder.decode(
                        data[0].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String email = URLDecoder.decode(
                        data[1].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String password = URLDecoder.decode(
                        data[2].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                User user = new User(
                        name,
                        email,
                        password,
                        "JOB_SEEKER"
                );

                UserDAO userDAO = new UserDAO();

                boolean registered =
                        userDAO.registerUser(user);

                if (registered) {
                    response = "Registration Successful!";
                } else {
                    response = "Registration Failed!";
                }

            } else {

                response = "Only POST request is allowed.";
            }

            byte[] responseBytes =
                    response.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "text/plain"
            );

            exchange.sendResponseHeaders(
                    200,
                    responseBytes.length
            );

            exchange.getResponseBody().write(responseBytes);

            exchange.close();
        });


        // ==========================================
// LOGIN API
// ==========================================

        server.createContext("/api/login", exchange -> {

            String response;

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                String body = new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );

                String[] data = body.split("&");

                String email = URLDecoder.decode(
                        data[0].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String password = URLDecoder.decode(
                        data[1].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );


                // Check user from MySQL

                UserDAO userDAO = new UserDAO();

                User user =
                        userDAO.loginUser(email, password);


                if (user != null) {

                    // Send user details as JSON

                    response =
                            "{"
                                    + "\"id\":" + user.getId() + ","
                                    + "\"name\":\"" + user.getName() + "\","
                                    + "\"email\":\"" + user.getEmail() + "\","
                                    + "\"role\":\"" + user.getRole() + "\""
                                    + "}";

                } else {

                    response =
                            "{\"error\":\"Invalid Email or Password!\"}";
                }

            } else {

                response =
                        "{\"error\":\"Only POST request is allowed.\"}";
            }


            byte[] responseBytes =
                    response.getBytes(StandardCharsets.UTF_8);


            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "application/json"
            );


            exchange.sendResponseHeaders(
                    200,
                    responseBytes.length
            );


            exchange.getResponseBody().write(
                    responseBytes
            );


            exchange.close();
        });

        // ==========================================
// GET ALL JOBS API
// ==========================================

        server.createContext("/api/jobs", exchange -> {

            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {

                JobDAO jobDAO = new JobDAO();

                java.util.List<Job> jobs =
                        jobDAO.getAllJobs();

                StringBuilder response =
                        new StringBuilder();

                for (Job job : jobs) {

                    response.append("{");

                    response.append("\"id\":")
                            .append(job.getId())
                            .append(",");

                    response.append("\"title\":\"")
                            .append(job.getTitle())
                            .append("\",");

                    response.append("\"description\":\"")
                            .append(job.getDescription())
                            .append("\",");

                    response.append("\"skills\":\"")
                            .append(job.getSkills())
                            .append("\",");

                    response.append("\"salary\":\"")
                            .append(job.getSalary())
                            .append("\",");

                    response.append("\"location\":\"")
                            .append(job.getLocation())
                            .append("\",");

                    response.append("\"jobType\":\"")
                            .append(job.getJobType())
                            .append("\"");

                    response.append("},");
                }

                // Remove last comma
                if (!jobs.isEmpty()) {
                    response.deleteCharAt(response.length() - 1);
                }

                String json =
                        "[" + response + "]";

                byte[] responseBytes =
                        json.getBytes(StandardCharsets.UTF_8);

                exchange.getResponseHeaders().set(
                        "Content-Type",
                        "application/json"
                );

                exchange.sendResponseHeaders(
                        200,
                        responseBytes.length
                );

                exchange.getResponseBody()
                        .write(responseBytes);

            } else {

                String response =
                        "Only GET request is allowed.";

                byte[] responseBytes =
                        response.getBytes(StandardCharsets.UTF_8);

                exchange.sendResponseHeaders(
                        405,
                        responseBytes.length
                );

                exchange.getResponseBody()
                        .write(responseBytes);
            }

            exchange.close();
        });

        // ==========================================
// APPLY FOR JOB API
// ==========================================

        server.createContext("/api/apply", exchange -> {

            String response;

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                String body = new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );

                String[] data = body.split("&");

                int jobId = Integer.parseInt(
                        data[0].split("=", 2)[1]
                );

                int userId = Integer.parseInt(
                        data[1].split("=", 2)[1]
                );

                Application application =
                        new Application(jobId, userId);

                ApplicationDAO applicationDAO =
                        new ApplicationDAO();

                boolean applied =
                        applicationDAO.applyForJob(application);

                if (applied) {
                    response = "Application Submitted Successfully!";
                } else {
                    response = "Application Failed!";
                }

            } else {

                response = "Only POST request is allowed.";
            }

            byte[] responseBytes =
                    response.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "text/plain"
            );

            exchange.sendResponseHeaders(
                    200,
                    responseBytes.length
            );

            exchange.getResponseBody()
                    .write(responseBytes);

            exchange.close();
        });

        // ==========================================
// GET USER APPLICATIONS API
// ==========================================

        server.createContext("/api/applications", exchange -> {

            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {

                String query =
                        exchange.getRequestURI().getQuery();

                String[] parts = query.split("=");

                int userId =
                        Integer.parseInt(parts[1]);

                ApplicationDAO applicationDAO =
                        new ApplicationDAO();

                java.util.List<Application> applications =
                        applicationDAO.getUserApplications(userId);

                StringBuilder response =
                        new StringBuilder();

                response.append("[");

                for (Application application : applications) {

                    response.append("{");

                    response.append("\"id\":")
                            .append(application.getId())
                            .append(",");

                    response.append("\"jobId\":")
                            .append(application.getJobId())
                            .append(",");

                    response.append("\"userId\":")
                            .append(application.getUserId())
                            .append(",");

                    response.append("\"applicationDate\":\"")
                            .append(application.getApplicationDate())
                            .append("\",");

                    response.append("\"status\":\"")
                            .append(application.getStatus())
                            .append("\"");

                    response.append("},");
                }

                if (!applications.isEmpty()) {
                    response.deleteCharAt(response.length() - 1);
                }

                response.append("]");

                byte[] responseBytes =
                        response.toString()
                                .getBytes(StandardCharsets.UTF_8);

                exchange.getResponseHeaders().set(
                        "Content-Type",
                        "application/json"
                );

                exchange.sendResponseHeaders(
                        200,
                        responseBytes.length
                );

                exchange.getResponseBody()
                        .write(responseBytes);

            } else {

                String response =
                        "Only GET request is allowed.";

                byte[] responseBytes =
                        response.getBytes(StandardCharsets.UTF_8);

                exchange.sendResponseHeaders(
                        405,
                        responseBytes.length
                );

                exchange.getResponseBody()
                        .write(responseBytes);
            }

            exchange.close();
        });

        // ==========================================
// POST JOB API
// ==========================================

        server.createContext("/api/post-job", exchange -> {

            String response;

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                String body = new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );

                String[] data = body.split("&");

                String title = URLDecoder.decode(
                        data[0].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String description = URLDecoder.decode(
                        data[1].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String skills = URLDecoder.decode(
                        data[2].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String salary = URLDecoder.decode(
                        data[3].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String location = URLDecoder.decode(
                        data[4].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String jobType = URLDecoder.decode(
                        data[5].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );


                // Company ID 1 = ABC Technologies
                Job job = new Job(
                        title,
                        description,
                        skills,
                        salary,
                        location,
                        jobType,
                        1
                );


                JobDAO jobDAO = new JobDAO();

                boolean added =
                        jobDAO.addJob(job);


                if (added) {

                    response =
                            "Job Posted Successfully!";

                } else {

                    response =
                            "Job Posting Failed!";
                }

            } else {

                response =
                        "Only POST request is allowed.";
            }


            byte[] responseBytes =
                    response.getBytes(StandardCharsets.UTF_8);


            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "text/plain"
            );

            exchange.sendResponseHeaders(
                    200,
                    responseBytes.length
            );

            exchange.getResponseBody()
                    .write(responseBytes);

            exchange.close();
        });

        // ==========================================
        // UPDATE JOB API
        // ==========================================

        server.createContext("/api/update-job", exchange -> {

            String response;

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                String body = new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );

                String[] data = body.split("&");

                int id = Integer.parseInt(
                        data[0].split("=", 2)[1]
                );

                String title = URLDecoder.decode(
                        data[1].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String description = URLDecoder.decode(
                        data[2].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String skills = URLDecoder.decode(
                        data[3].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String salary = URLDecoder.decode(
                        data[4].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String location = URLDecoder.decode(
                        data[5].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );

                String jobType = URLDecoder.decode(
                        data[6].split("=", 2)[1],
                        StandardCharsets.UTF_8
                );


                Job job = new Job();

                job.setId(id);
                job.setTitle(title);
                job.setDescription(description);
                job.setSkills(skills);
                job.setSalary(salary);
                job.setLocation(location);
                job.setJobType(jobType);


                JobDAO jobDAO = new JobDAO();

                boolean updated =
                        jobDAO.updateJob(job);


                if (updated) {

                    response =
                            "Job Updated Successfully!";

                } else {

                    response =
                            "Job Update Failed!";
                }

            } else {

                response =
                        "Only POST request is allowed.";
            }


            byte[] responseBytes =
                    response.getBytes(StandardCharsets.UTF_8);


            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "text/plain"
            );


            exchange.sendResponseHeaders(
                    200,
                    responseBytes.length
            );


            exchange.getResponseBody().write(
                    responseBytes
            );

            exchange.close();
        });

        // ==========================================
        // DELETE JOB API
        // ==========================================

        server.createContext("/api/delete-job", exchange -> {

            String response;

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {

                String body = new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );


                String[] data =
                        body.split("=");


                int jobId =
                        Integer.parseInt(data[1]);


                JobDAO jobDAO =
                        new JobDAO();


                boolean deleted =
                        jobDAO.deleteJob(jobId);


                if (deleted) {

                    response =
                            "Job Deleted Successfully!";

                } else {

                    response =
                            "Job Delete Failed!";
                }

            } else {

                response =
                        "Only POST request is allowed.";
            }


            byte[] responseBytes =
                    response.getBytes(StandardCharsets.UTF_8);


            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "text/plain"
            );


            exchange.sendResponseHeaders(
                    200,
                    responseBytes.length
            );


            exchange.getResponseBody().write(
                    responseBytes
            );


            exchange.close();
        });

        // ==========================================
        // SERVE HTML / CSS / JS
        // ==========================================

        server.createContext("/", exchange -> {

            String requestPath =
                    exchange.getRequestURI().getPath();


            if (requestPath.equals("/")) {
                requestPath = "/index.html";
            }


            Path file =
                    Path.of("Web" + requestPath);


            if (Files.exists(file)) {

                byte[] response =
                        Files.readAllBytes(file);

                String contentType =
                        "text/html";


                if (requestPath.endsWith(".css")) {

                    contentType =
                            "text/css";

                } else if (requestPath.endsWith(".js")) {

                    contentType =
                            "application/javascript";
                }


                exchange.getResponseHeaders().set(
                        "Content-Type",
                        contentType
                );


                exchange.sendResponseHeaders(
                        200,
                        response.length
                );


                exchange.getResponseBody().write(
                        response
                );


            } else {

                String response =
                        "404 - File Not Found";

                byte[] responseBytes =
                        response.getBytes(StandardCharsets.UTF_8);


                exchange.sendResponseHeaders(
                        404,
                        responseBytes.length
                );


                exchange.getResponseBody().write(
                        responseBytes
                );
            }


            exchange.close();
        });


        // ==========================================
        // START SERVER
        // ==========================================

        server.start();

        System.out.println(
                "Job Portal started at http://localhost:8080"
        );
    }
}