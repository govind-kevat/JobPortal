// ==========================================
// LOAD JOBS FROM MYSQL
// ==========================================

async function loadJobs() {

    const jobList =
        document.getElementById("jobList");

    if (!jobList) {
        return;
    }

    try {

        const response =
            await fetch("/api/jobs");

        const jobs =
            await response.json();

        jobList.innerHTML = "";

        if (jobs.length === 0) {

            jobList.innerHTML =
                "<p>No jobs available.</p>";

            return;
        }

        jobs.forEach(function (job) {

            const jobCard =
                document.createElement("div");

            jobCard.className =
                "job-card";

            jobCard.innerHTML = `

                <h3>${job.title}</h3>

                <p>${job.description}</p>

                <p>
                    <strong>Skills:</strong>
                    ${job.skills}
                </p>

                <p>
                    <strong>Location:</strong>
                    ${job.location}
                </p>

                <p>
                    <strong>Salary:</strong>
                    ${job.salary}
                </p>

                <p>
                    <strong>Job Type:</strong>
                    ${job.jobType}
                </p>

                <button onclick="applyJob(${job.id})">
                    Apply Now
                </button>

            `;

            jobList.appendChild(jobCard);

        });

    } catch (error) {

        console.error(
            "Failed to load jobs:",
            error
        );

        jobList.innerHTML =
            "<p>Failed to load jobs.</p>";
    }
}


// ==========================================
// SEARCH JOBS
// ==========================================

function searchJobs() {

    const input =
        document.getElementById("searchInput");

    const locationInput =
        document.getElementById("locationInput");


    const searchText =
        input
            ? input.value.toLowerCase().trim()
            : "";


    const locationText =
        locationInput
            ? locationInput.value.toLowerCase().trim()
            : "";


    const jobCards =
        document.querySelectorAll(".job-card");


    let found = false;


    jobCards.forEach(function (card) {

        const jobText =
            card.innerText.toLowerCase();


        const matchesJob =
            searchText === "" ||
            jobText.includes(searchText);


        const matchesLocation =
            locationText === "" ||
            jobText.includes(locationText);


        if (
            matchesJob &&
            matchesLocation
        ) {

            card.style.display =
                "block";

            found = true;

        } else {

            card.style.display =
                "none";
        }

    });


    if (!found) {

        alert("No jobs found!");

    }
}


// ==========================================
// CATEGORY FILTER
// ==========================================

function filterCategory(category) {

    const jobCards =
        document.querySelectorAll(".job-card");


    const categoryText =
        category.toLowerCase();


    let found = false;


    jobCards.forEach(function (card) {

        const jobText =
            card.innerText.toLowerCase();


        if (
            jobText.includes(categoryText)
        ) {

            card.style.display =
                "block";

            found = true;

        } else {

            card.style.display =
                "none";
        }

    });


    document
        .getElementById("jobs")
        ?.scrollIntoView({
            behavior: "smooth"
        });


    if (!found) {

        alert(
            "No jobs found for " +
            category
        );
    }
}


// ==========================================
// APPLY FOR JOB
// ==========================================

async function applyJob(jobId) {

    const userId =
        localStorage.getItem("userId");


    // Check login

    if (!userId) {

        alert(
            "Please login first to apply for a job."
        );

        window.location.href =
            "login.html";

        return;
    }


    const data =
        "jobId=" +
        jobId +
        "&userId=" +
        userId;


    try {

        const response =
            await fetch(
                "/api/apply",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/x-www-form-urlencoded"
                    },

                    body: data
                }
            );


        const result =
            await response.text();


        alert(result);


    } catch (error) {

        console.error(error);

        alert(
            "Server connection failed!"
        );
    }
}


// ==========================================
// LOGIN
// ==========================================

async function loginUser() {

    const emailElement =
        document.getElementById("email");

    const passwordElement =
        document.getElementById("password");


    const email =
        emailElement.value.trim();

    const password =
        passwordElement.value;


    if (
        email === "" ||
        password === ""
    ) {

        alert(
            "Please enter email and password."
        );

        return;
    }


    const data =
        "email=" +
        encodeURIComponent(email) +
        "&password=" +
        encodeURIComponent(password);


    try {

        const response =
            await fetch(
                "/api/login",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/x-www-form-urlencoded"
                    },

                    body: data
                }
            );


        const result =
            await response.json();


        if (result.error) {

            alert(result.error);

            return;
        }


        // ==========================================
        // SAVE USER DETAILS
        // ==========================================

        localStorage.setItem(
            "userId",
            result.id
        );

        localStorage.setItem(
            "userName",
            result.name
        );

        localStorage.setItem(
            "userEmail",
            result.email
        );

        localStorage.setItem(
            "userRole",
            result.role
        );

        localStorage.setItem(
            "isLoggedIn",
            "true"
        );


        alert(
            "Login Successful! Welcome " +
            result.name
        );


        // ==========================================
        // REDIRECT BASED ON ROLE
        // ==========================================

        if (
            result.role === "JOB_SEEKER"
        ) {

            window.location.href =
                "dashboard.html";

        } else if (
            result.role === "RECRUITER"
        ) {

            window.location.href =
                "post-job.html";
        }


    } catch (error) {

        console.error(error);

        alert(
            "Server connection failed!"
        );
    }
}


// ==========================================
// REGISTER
// ==========================================

async function registerUser() {

    const name =
        document.getElementById("name")
            .value
            .trim();


    const email =
        document.getElementById("registerEmail")
            .value
            .trim();


    const password =
        document.getElementById("registerPassword")
            .value;


    if (
        name === "" ||
        email === "" ||
        password === ""
    ) {

        alert(
            "Please fill all fields."
        );

        return;
    }


    const data =
        "name=" +
        encodeURIComponent(name) +

        "&email=" +
        encodeURIComponent(email) +

        "&password=" +
        encodeURIComponent(password);


    try {

        const response =
            await fetch(
                "/api/register",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/x-www-form-urlencoded"
                    },

                    body: data
                }
            );


        const result =
            await response.text();


        alert(result);


    } catch (error) {

        console.error(error);

        alert(
            "Server connection failed!"
        );
    }
}


// ==========================================
// LOAD USER DASHBOARD
// ==========================================

async function loadDashboard() {

    const userId =
        localStorage.getItem("userId");

    const userName =
        localStorage.getItem("userName");

    const userEmail =
        localStorage.getItem("userEmail");


    // ==========================================
    // CHECK LOGIN
    // ==========================================

    if (!userId) {

        window.location.href =
            "login.html";

        return;
    }


    // ==========================================
    // SHOW USER DETAILS
    // ==========================================

    document.getElementById(
        "userName"
    ).innerText = userName;


    document.getElementById(
        "profileName"
    ).innerText = userName;


    document.getElementById(
        "profileEmail"
    ).innerText = userEmail;


    // ==========================================
    // GET APPLICATIONS
    // ==========================================

    try {

        const response =
            await fetch(
                "/api/applications?userId=" +
                userId
            );


        const applications =
            await response.json();


        // ==========================================
        // APPLICATION COUNTS
        // ==========================================

        document.getElementById(
            "totalApplications"
        ).innerText =
            applications.length;


        let pending = 0;
        let selected = 0;
        let rejected = 0;


        applications.forEach(
            function (application) {

                const status =
                    application.status
                        .toUpperCase();


                if (
                    status === "PENDING" ||
                    status === "APPLIED"
                ) {

                    pending++;

                } else if (
                    status === "SELECTED"
                ) {

                    selected++;

                } else if (
                    status === "REJECTED"
                ) {

                    rejected++;
                }

            }
        );


        document.getElementById(
            "pendingApplications"
        ).innerText =
            pending;


        document.getElementById(
            "selectedApplications"
        ).innerText =
            selected;


        document.getElementById(
            "rejectedApplications"
        ).innerText =
            rejected;


        // ==========================================
        // SHOW APPLICATION LIST
        // ==========================================

        const applicationList =
            document.getElementById(
                "dashboardApplicationList"
            );


        if (
            applications.length === 0
        ) {

            applicationList.innerHTML =
                "<p>No applications found.</p>";

            return;
        }


        applicationList.innerHTML = "";


        applications.forEach(
            function (application) {

                const card =
                    document.createElement("div");


                card.className =
                    "application-card";


                // ==========================================
                // STATUS CLASS
                // ==========================================

                let statusClass =
                    "status-applied";


                if (
                    application.status ===
                    "PENDING"
                ) {

                    statusClass =
                        "status-pending";

                } else if (
                    application.status ===
                    "SELECTED"
                ) {

                    statusClass =
                        "status-selected";

                } else if (
                    application.status ===
                    "REJECTED"
                ) {

                    statusClass =
                        "status-rejected";
                }


                // ==========================================
                // APPLICATION CARD
                // ==========================================

                card.innerHTML = `

                    <div class="application-card-header">

                        <div>

                            <h3>
                                ${application.jobTitle}
                            </h3>

                            <p>
                                🏢
                                ${application.companyName}
                            </p>

                        </div>


                        <span class="status-badge ${statusClass}">
                            ${application.status}
                        </span>

                    </div>


                    <p>
                        📍 ${application.location}
                    </p>


                    <p>
                        💰 ${application.salary}
                    </p>


                    <p>
                        📅 Applied:
                        ${application.applicationDate}
                    </p>


                    <p>
                        🆔 Application ID:
                        ${application.id}
                    </p>

                `;


                applicationList.appendChild(
                    card
                );

            }
        );


    } catch (error) {

        console.error(
            "Dashboard error:",
            error
        );

    }
}


// ==========================================
// LOAD MY APPLICATIONS
// ==========================================

async function loadApplications() {

    const applicationList =
        document.getElementById(
            "applicationList"
        );


    if (!applicationList) {
        return;
    }


    const userId =
        localStorage.getItem("userId");


    if (!userId) {
        return;
    }


    try {

        const response =
            await fetch(
                "/api/applications?userId=" +
                userId
            );


        const applications =
            await response.json();


        if (
            applications.length === 0
        ) {

            applicationList.innerHTML =
                "<p>No applications found.</p>";

            return;
        }


        applicationList.innerHTML = "";


        applications.forEach(
            function (application) {

                const card =
                    document.createElement("div");


                card.className =
                    "job-card";


                card.innerHTML = `

                    <h3>
                        Application #${application.id}
                    </h3>

                    <p>
                        Job:
                        ${application.jobTitle}
                    </p>

                    <p>
                        Company:
                        ${application.companyName}
                    </p>

                    <p>
                        Application Date:
                        ${application.applicationDate}
                    </p>

                    <p>
                        Status:
                        ${application.status}
                    </p>

                `;


                applicationList.appendChild(
                    card
                );

            }
        );


    } catch (error) {

        console.error(error);

        applicationList.innerHTML =
            "<p>Failed to load applications.</p>";
    }
}


// ==========================================
// RECRUITER - SHOW FORM
// ==========================================

function showJobForm() {

    const form =
        document.getElementById(
            "jobForm"
        );


    if (form) {

        form.style.display =
            "block";


        form.scrollIntoView({
            behavior: "smooth"
        });
    }
}


// ==========================================
// RECRUITER - HIDE FORM
// ==========================================

function hideJobForm() {

    const form =
        document.getElementById(
            "jobForm"
        );


    if (form) {

        form.style.display =
            "none";
    }
}


// ==========================================
// POST JOB
// ==========================================

async function postJob() {

    const title =
        document.getElementById(
            "jobTitle"
        ).value.trim();


    const description =
        document.getElementById(
            "jobDescription"
        ).value.trim();


    const skills =
        document.getElementById(
            "jobSkills"
        ).value.trim();


    const salary =
        document.getElementById(
            "jobSalary"
        ).value.trim();


    const location =
        document.getElementById(
            "jobLocation"
        ).value.trim();


    const jobType =
        document.getElementById(
            "jobType"
        ).value.trim();


    if (
        title === "" ||
        description === "" ||
        skills === "" ||
        salary === "" ||
        location === "" ||
        jobType === ""
    ) {

        alert(
            "Please fill all fields."
        );

        return;
    }


    const data =
        "title=" +
        encodeURIComponent(title) +

        "&description=" +
        encodeURIComponent(description) +

        "&skills=" +
        encodeURIComponent(skills) +

        "&salary=" +
        encodeURIComponent(salary) +

        "&location=" +
        encodeURIComponent(location) +

        "&jobType=" +
        encodeURIComponent(jobType);


    try {

        const response =
            await fetch(
                "/api/post-job",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/x-www-form-urlencoded"
                    },

                    body: data
                }
            );


        const result =
            await response.text();


        alert(result);


        if (response.ok) {

            document.getElementById(
                "jobTitle"
            ).value = "";


            document.getElementById(
                "jobDescription"
            ).value = "";


            document.getElementById(
                "jobSkills"
            ).value = "";


            document.getElementById(
                "jobSalary"
            ).value = "";


            document.getElementById(
                "jobLocation"
            ).value = "";


            document.getElementById(
                "jobType"
            ).value = "";


            hideJobForm();

            loadRecruiterJobs();

            loadJobs();
        }


    } catch (error) {

        console.error(error);

        alert(
            "Server connection failed!"
        );
    }
}


// ==========================================
// LOAD RECRUITER JOBS
// ==========================================

async function loadRecruiterJobs() {

    const jobList =
        document.getElementById(
            "recruiterJobList"
        );


    if (!jobList) {
        return;
    }


    try {

        const response =
            await fetch("/api/jobs");


        const jobs =
            await response.json();


        if (
            jobs.length === 0
        ) {

            jobList.innerHTML =
                "<p>No jobs posted yet.</p>";

            return;
        }


        jobList.innerHTML = "";


        jobs.forEach(function (job) {

            const card =
                document.createElement("div");


            card.className =
                "recruiter-job-card";


            card.innerHTML = `

                <h3>
                    ${job.title}
                </h3>

                <p>
                    🏢 Company ID:
                    ${job.companyId}
                </p>

                <p>
                    📍 ${job.location}
                </p>

                <p>
                    💰 ${job.salary}
                </p>

                <p>
                    💼 ${job.jobType}
                </p>

                <p>
                    🛠️ ${job.skills}
                </p>

                <p>
                    ${job.description}
                </p>


                <div class="job-actions">

                    <button
                        class="edit-btn"
                        onclick="editJob(${job.id})">

                        Edit

                    </button>


                    <button
                        class="delete-btn"
                        onclick="deleteJob(${job.id})">

                        Delete

                    </button>

                </div>

            `;


            jobList.appendChild(card);

        });


    } catch (error) {

        console.error(error);

        jobList.innerHTML =
            "<p>Failed to load jobs.</p>";
    }
}


// ==========================================
// EDIT JOB
// ==========================================

async function editJob(jobId) {

    const title =
        prompt(
            "Enter new Job Title:"
        );


    if (title === null) {
        return;
    }


    const description =
        prompt(
            "Enter new Job Description:"
        );


    if (description === null) {
        return;
    }


    const skills =
        prompt(
            "Enter Required Skills:"
        );


    if (skills === null) {
        return;
    }


    const salary =
        prompt(
            "Enter Salary:"
        );


    if (salary === null) {
        return;
    }


    const location =
        prompt(
            "Enter Location:"
        );


    if (location === null) {
        return;
    }


    const jobType =
        prompt(
            "Enter Job Type:"
        );


    if (jobType === null) {
        return;
    }


    const data =
        "id=" +
        jobId +

        "&title=" +
        encodeURIComponent(
            title.trim()
        ) +

        "&description=" +
        encodeURIComponent(
            description.trim()
        ) +

        "&skills=" +
        encodeURIComponent(
            skills.trim()
        ) +

        "&salary=" +
        encodeURIComponent(
            salary.trim()
        ) +

        "&location=" +
        encodeURIComponent(
            location.trim()
        ) +

        "&jobType=" +
        encodeURIComponent(
            jobType.trim()
        );


    try {

        const response =
            await fetch(
                "/api/update-job",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/x-www-form-urlencoded"
                    },

                    body: data
                }
            );


        const result =
            await response.text();


        alert(result);


        if (response.ok) {

            loadRecruiterJobs();

            loadJobs();
        }


    } catch (error) {

        console.error(error);

        alert(
            "Server error while updating job."
        );
    }
}


// ==========================================
// DELETE JOB
// ==========================================

async function deleteJob(jobId) {

    const confirmDelete =
        confirm(
            "Are you sure you want to delete this job?"
        );


    if (!confirmDelete) {
        return;
    }


    const data =
        "jobId=" + jobId;


    try {

        const response =
            await fetch(
                "/api/delete-job",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/x-www-form-urlencoded"
                    },

                    body: data
                }
            );


        const result =
            await response.text();


        alert(result);


        if (response.ok) {

            loadRecruiterJobs();

            loadJobs();
        }


    } catch (error) {

        console.error(error);

        alert(
            "Server error while deleting job."
        );
    }
}


// ==========================================
// PAGE LOAD
// ==========================================

window.addEventListener("DOMContentLoaded", function () {

    // Home page
    if (document.getElementById("jobList")) {
        loadJobs();
    }

    // Applications page
    if (document.getElementById("applicationList")) {
        loadApplications();
    }

    // Recruiter page
    if (document.getElementById("recruiterJobList")) {
        loadRecruiterJobs();
    }

    // Dashboard page
    if (document.getElementById("dashboardApplicationList")) {
        loadDashboard();
    }

});