<%@ page contentType ="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>User Profile</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

    <!-- Profile page styles -->
    <link href="style.css" rel="stylesheet">
</head>

<body class="bg-simple-light">

    <!-- NAVBAR -->
    <nav class="navbar navbar-expand-lg navbar-dark border-bottom border-simple">
        <div class="container">
            <a class="navbar-brand fw-bold" href="#">

                <img src="img/logo.png" alt="My Logo" class="img-fluid mb-1" style="max-height: 30px;"> Code InTo Guys
            </a>

            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Q&A</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">SNS</a></li>
                </ul>
                <a href="logout.jsp" class="btn btn-outline-danger btn-sm ms-3">Logout</a>
            </div>
        </div>
    </nav>

    <!-- MAIN -->
    <main>
        <div class="container my-4">
            <div class="row">

                <!-- SIDEBAR -->
                <div class="col-md-3 mb-3">
                    <div class="card">
                        <div class="card-body text-center">
                            <!-- Profile photo: you will set img src from JSP (e.g. user's saved photo path or default) -->
                            <img src="img/prof.jpeg" class="rounded-circle mb-3 img-fluid profile-photo" alt="Profile"
                                id="profile-photo-img">

                            <!-- Profile photo upload - handle in JSP (Tomcat): receive multipart form, save file, update user's photo path -->
                            <form method="post" enctype="multipart/form-data" class="profile-photo-form" action="#">
                                <!-- TODO: set action to your JSP that handles upload, e.g. action="uploadProfilePhoto.jsp" -->
                                <label class="d-block mb-1">
                                    <span class="btn btn-simple btn-sm"><i class="fas fa-camera me-1"></i> Choose
                                        photo</span>
                                    <input type="file" name="profilePhoto" accept="image/*" class="d-none">
                                </label>
                                <button type="submit" class="btn btn-outline-simple btn-sm">Upload</button>
                                <!-- You will handle this form in JSP: get Part for "profilePhoto", save to disk/db, redirect back or show updated img -->
                            </form>

                            <h5 class="mb-0">${userList.get(5)}</h5>
                            

                            <hr>

                            <!-- Toggle: same card shows either view or form (no pop-up). No JS. -->
                            <input type="checkbox" id="profile-edit-toggle" class="d-none" aria-hidden="true">
                            <ul class="list-group list-group-flush text-start profile-view">
                                <li class="list-group-item d-flex align-items-center">
                                    <i class="fas fa-user me-2"></i>
                                    <span class="flex-grow-1" id="profile-fullname">${userList.get(1)}</span>
                                </li>
                                <li class="list-group-item d-flex align-items-center">
                                    <i class="fas fa-lightbulb me-2"></i>
                                    <span class="flex-grow-1" id="profile-skills">${userList.get(3)}</span>
                                </li>
                                <li class="list-group-item d-flex align-items-center">
                                    <i class="fas fa-medal me-2"></i>
                                    <span class="flex-grow-1" id="profile-school">${userList.get(4)}</span>
                                </li>
                            </ul>
                            <label for="profile-edit-toggle" class="btn btn-simple btn-sm w-100 my-2 profile-edit-btn">
                                <i class="fas fa-pen me-1"></i> Edit
                            </label>
                            <div class="profile-edit-wrap">
                                <form method="post" action="ProfileEditServlet" class="profile-edit-form">
                                    <ul class="list-group list-group-flush text-start">
                                        <li class="list-group-item d-flex align-items-center">
                                            <i class="fas fa-user me-2"></i>
                                            <input type="text" name="fullname" class="form-control form-control-sm border-0 bg-transparent flex-grow-1" placeholder="Full name" value="Full Namexxxx">
                                        </li>
                                        <li class="list-group-item d-flex align-items-center">
                                            <i class="fas fa-lightbulb me-2"></i>
                                            <input type="text" name="skills" class="form-control form-control-sm border-0 bg-transparent flex-grow-1" placeholder="Skills" value="skillsxxxx">
                                        </li>
                                        <li class="list-group-item d-flex align-items-center">
                                            <i class="fas fa-medal me-2"></i>
                                            <input type="text" name="school" class="form-control form-control-sm border-0 bg-transparent flex-grow-1" placeholder="School" value="Schooxxxxxl">
                                        </li>
                                    </ul>
                                    <div class="d-flex gap-1 mt-2">
                                        <button type="submit" class="btn btn-simple btn-sm flex-grow-1">
                                            <i class="fas fa-check me-1"></i> Save
                                        </button>
                                        <label for="profile-edit-toggle" class="btn btn-outline-simple btn-sm flex-grow-1 mb-0" style="cursor:pointer">
                                            Cancel
                                        </label>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- CONTENT -->
                <div class="col-md-9">

                    <!-- PROFILE INFO -->
                    <div class="card mb-3 border-simple">
                        <div class="card-body">
                            <input type="checkbox" id="about-edit-toggle" class="d-none" aria-hidden="true">
                            <h4 class="text-simple-dark d-flex align-items-center flex-wrap gap-2 about-heading-view">
                                About
                                <label for="about-edit-toggle" class="btn btn-simple btn-sm about-edit-btn mb-0" style="cursor:pointer">
                                    <i class="fas fa-pen me-1"></i> Edit
                                </label>
                            </h4>
                            <div class="about-view">
                                <p class="text-simple-gray mb-0" id="profile-about">${userList.get(2)}
                                    Java..</p>
                            </div>

                            <div class="divider py-1 bg-dark mb-4"></div>

                            
                            <div class="about-edit-wrap ">
                                <h4 class="text-simple-dark mb-2">About</h4>
                                <form method="post" action="UpdateAboutServlet" class="about-edit-form">
                                    <textarea name="about" class="form-control border-simple text-simple-gray mb-2" rows="3" placeholder="Write a short bio about yourself...">Passionate developer who loves Java..</textarea>
                                    <div class="d-flex gap-1">
                                        <button type="submit" class="btn btn-simple btn-sm flex-grow-1">
                                            <i class="fas fa-check me-1"></i> Save
                                        </button>
                                        <label for="about-edit-toggle" class="btn btn-outline-simple btn-sm flex-grow-1 mb-0" style="cursor:pointer">
                                            Cancel
                                        </label>
                                    </div>
                                </form>
                            </div>
                            
                            <div class="row text-center">
                                <div class="col">
                                    <h6 class="text-simple-gray">SNS Posts</h6>
                                    <p class="fw-bold text-simple-dark">8</p>
                                </div>
                                <div class="col">
                                    <h6 class="text-simple-gray">Questions</h6>
                                    <p class="fw-bold text-simple-dark">12</p>
                                </div>
                                <div class="col">
                                    <h6 class="text-simple-gray">Answers</h6>
                                    <p class="fw-bold text-simple-dark">45</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- TABS -->
                    <ul class="nav nav-tabs mb-3 border-simple">
                        <li class="nav-item">
                            <a class="nav-link active text-simple-dark border-simple" data-bs-toggle="tab"
                                href="#activity">Activity</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-simple-gray" data-bs-toggle="tab" href="#questions">Questions</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-simple-gray" data-bs-toggle="tab" href="#answers">Answers</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-simple-gray" data-bs-toggle="tab" href="#snsposts">SNS Posts</a>
                        </li>
                    </ul>

                    <div class="tab-content">

                        <!-- ACTIVITY -->
                        <div class="tab-pane fade show active" id="activity">
                            <div class="list-group">
                                <div class="list-group-item border-simple">
                                    Answered a question
                                    <small class="text-simple-gray d-block">2 hours ago</small>
                                </div>
                                <div class="list-group-item border-simple">
                                    Asked a question
                                    <small class="text-simple-gray d-block">1 day ago</small>
                                </div>
                            </div>
                        </div>

                        <!-- QUESTIONS -->
                        <div class="tab-pane fade" id="questions">
                            <div class="list-group">
                                <div class="list-group-item border-simple">Your questions will appear here.</div>
                            </div>
                        </div>

                        <!-- ANSWERS -->
                        <div class="tab-pane fade" id="answers">
                            <div class="list-group">
                                <div class="list-group-item border-simple">Your answers will appear here.</div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="answers">
                            <div class="list-group">
                                <div class="list-group-item border-simple">Your answers will appear here.</div>
                            </div>
                        </div>
                        

                        <!-- SNS POSTS -->
                        <div class="tab-pane fade" id="snsposts">
                            <div class="list-group">
                                <div class="list-group-item border-simple">sns1</div>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>