<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile — Guys Into Code</title>
    <script src="js/theme-toggle.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
      tailwind.config = { darkMode: 'class',
        theme: {
          extend: {
            fontFamily: { sans: ['-apple-system', 'BlinkMacSystemFont', 'Segoe UI', 'Roboto', 'sans-serif'] },
            colors: {
              reddit: { orange: '#FF4500', orangeHover: '#cc3700', blue: '#0079D3', bg: '#DAE0E6', card: '#ffffff', border: '#EDEFF1', text: '#1c1c1c', secondary: '#787c7e', muted: '#a8aaab', hover: '#f6f7f8', darkBg: '#030303', darkCard: '#1a1a1b', darkBorder: '#343536', darkText: '#d7dadc', darkHover: '#2d2d2e' }
            }
          }
        }
      }
    </script>
    <style>
      .tab-pane { display: none; }
      .tab-pane.active { display: block; }
      .line-clamp-1 { overflow: hidden; text-overflow: ellipsis; display: -webkit-box; -webkit-line-clamp: 1; -webkit-box-orient: vertical; }
    </style>
</head>
<body class="bg-reddit-bg dark:bg-reddit-darkBg text-reddit-text dark:text-reddit-darkText font-sans min-h-screen transition-colors">

    <!-- Navbar -->
    <header class="sticky top-0 z-40 bg-reddit-card dark:bg-reddit-darkCard border-b border-reddit-border dark:border-reddit-darkBorder shadow-sm transition-colors">
        <div class="max-w-[1200px] mx-auto px-4">
            <div class="flex items-center justify-between h-12">
                <a href="Top" class="flex items-center gap-2 hover:opacity-80 transition-opacity">
                    <div class="w-8 h-8 rounded-full bg-reddit-orange flex items-center justify-center text-white text-sm"><i class="fas fa-code"></i></div>
                    <span class="text-lg font-bold text-reddit-text dark:text-reddit-darkText hidden sm:block">Guys<span class="text-reddit-orange">Into</span>Code</span>
                </a>
                <div class="flex items-center gap-2">
                    <a href="Top" class="text-sm text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover px-3 py-1.5 rounded-full transition-colors">Home</a>
                    <button type="button" onclick="themeToggle()" class="w-8 h-8 rounded-full flex items-center justify-center text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors" title="Toggle theme"><i class="fas fa-moon dark:hidden"></i><i class="fas fa-sun hidden dark:inline"></i></button>
                    <form method="post" action="Logout" class="inline">
                        <button type="submit" class="px-3 py-1.5 rounded-full border border-reddit-orange text-reddit-orange text-sm font-bold hover:bg-reddit-orange hover:text-white transition-colors">Logout</button>
                    </form>
                </div>
            </div>
        </div>
    </header>

    <main class="max-w-[960px] mx-auto px-4 py-5">
        <div class="grid grid-cols-1 lg:grid-cols-3 gap-4">

            <!-- Left: Profile Card -->
            <div class="lg:col-span-1">
                <div class="bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder transition-colors">
                    <!-- Banner inside card -->
                    <div class="h-24 rounded-t-md bg-gradient-to-r from-reddit-blue to-blue-400"></div>
                    <!-- Avatar overlapping banner -->
                    <div class="px-4 pb-4 text-center" style="margin-top: -48px;">
                        <img src="ProfileImage" alt="Profile" class="w-24 h-24 rounded-full mx-auto object-cover border-4 border-reddit-card dark:border-reddit-darkCard shadow-lg bg-reddit-card dark:bg-reddit-darkCard" id="profile-photo-img">
                        <form method="post" enctype="multipart/form-data" action="UploadImage" class="mt-3 mb-3">
                            <label class="inline-flex items-center gap-1.5 px-3 py-1.5 rounded-full bg-reddit-bg dark:bg-reddit-darkBg text-reddit-secondary dark:text-reddit-darkText text-xs font-medium hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors border border-reddit-border dark:border-reddit-darkBorder">
                                <i class="fas fa-camera"></i> Photo
                                <input type="file" name="profilePhoto" accept="image/*" class="hidden">
                            </label>
                            <button type="submit" class="ml-1 px-3 py-1.5 rounded-full text-reddit-blue text-xs font-bold hover:bg-blue-50 dark:hover:bg-reddit-darkHover transition-colors">Upload</button>
                        </form>
                        <h2 class="text-base font-bold text-reddit-text dark:text-reddit-darkText"><c:out value="${userList.get(5)}" escapeXml="true" /></h2>
                    </div>

                    <div class="border-t border-reddit-border dark:border-reddit-darkBorder p-4">
                        <input type="checkbox" id="profile-edit-toggle" class="hidden" aria-hidden="true">
                        <ul class="profile-view space-y-1 text-left">
                            <li class="flex items-center gap-2.5 py-2 text-sm text-reddit-secondary dark:text-reddit-darkText">
                                <i class="fas fa-user w-5 text-reddit-blue text-center text-xs"></i>
                                <span id="profile-fullname">
                                    <c:out value="${userList.get(1)}" escapeXml="true" />
                                    <c:if test="${empty userList.get(1)}">
                                        <c:out value="full_name" />
                                    </c:if>
                                </span>
                            </li>
                            <li class="flex items-center gap-2.5 py-2 text-sm text-reddit-secondary dark:text-reddit-darkText">
                                <i class="fas fa-lightbulb w-5 text-reddit-orange text-center text-xs"></i>
                                <span id="profile-skills">
                                    <c:out value="${userList.get(3)}" escapeXml="true" />
                                    <c:if test="${empty userList.get(3)}">
                                        <c:out value="skills" />
                                    </c:if>
                                </span>
                            </li>
                            <li class="flex items-center gap-2.5 py-2 text-sm text-reddit-secondary dark:text-reddit-darkText">
                                <i class="fas fa-medal w-5 text-yellow-500 text-center text-xs"></i>
                                <span id="profile-school">
                                    <c:out value="${userList.get(4)}" escapeXml="true" />
                                    <c:if test="${empty userList.get(4)}">
                                        <c:out value="school" />
                                    </c:if>
                                </span>
                            </li>
                        </ul>
                        <label for="profile-edit-toggle" class="profile-edit-btn block w-full mt-3 py-2 rounded-full bg-reddit-bg dark:bg-reddit-darkBg border border-reddit-border dark:border-reddit-darkBorder text-reddit-blue text-center text-xs font-bold hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">Edit Profile</label>
                        <div class="profile-edit-wrap mt-3">
                            <form method="post" action="ProfileEditServlet">
                                <div class="space-y-2">
                                    <div class="flex items-center gap-2">
                                        <i class="fas fa-user w-5 text-reddit-blue text-xs text-center"></i>
                                        <input type="text" name="fullname" value="${userList.get(1)}" placeholder="Full name" class="flex-1 rounded-md border border-reddit-border dark:border-reddit-darkBorder dark:bg-reddit-darkBg dark:text-reddit-darkText px-3 py-2 text-sm focus:ring-1 focus:ring-reddit-blue focus:border-reddit-blue outline-none">
                                    </div>
                                    <div class="flex items-center gap-2">
                                        <i class="fas fa-lightbulb w-5 text-reddit-orange text-xs text-center"></i>
                                        <input type="text" name="skills" value="${userList.get(3)}" placeholder="Skills" class="flex-1 rounded-md border border-reddit-border dark:border-reddit-darkBorder dark:bg-reddit-darkBg dark:text-reddit-darkText px-3 py-2 text-sm focus:ring-1 focus:ring-reddit-blue focus:border-reddit-blue outline-none">
                                    </div>
                                    <div class="flex items-center gap-2">
                                        <i class="fas fa-medal w-5 text-yellow-500 text-xs text-center"></i>
                                        <input type="text" name="school" value="${userList.get(4)}" placeholder="School" class="flex-1 rounded-md border border-reddit-border dark:border-reddit-darkBorder dark:bg-reddit-darkBg dark:text-reddit-darkText px-3 py-2 text-sm focus:ring-1 focus:ring-reddit-blue focus:border-reddit-blue outline-none">
                                    </div>
                                </div>
                                <div class="flex gap-2 mt-3">
                                    <button type="submit" class="flex-1 py-2 rounded-full bg-reddit-orange text-white text-xs font-bold hover:bg-reddit-orangeHover transition-colors">Save</button>
                                    <label for="profile-edit-toggle" class="flex-1 py-2 rounded-full border border-reddit-border dark:border-reddit-darkBorder text-center text-xs font-medium text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">Cancel</label>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Right: Content -->
            <div class="lg:col-span-2 space-y-4">

                <!-- About / Stats -->
                <div class="bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder transition-colors">
                    <div class="p-4">
                        <input type="checkbox" id="about-edit-toggle" class="hidden" aria-hidden="true">
                        <div class="about-heading-view flex items-center gap-2 flex-wrap">
                            <h3 class="text-sm font-bold text-reddit-text dark:text-reddit-darkText uppercase tracking-wide">About</h3>
                            <label for="about-edit-toggle" class="about-edit-btn px-2.5 py-1 rounded-full text-reddit-blue text-xs font-bold hover:bg-blue-50 dark:hover:bg-reddit-darkHover transition-colors">Edit</label>
                        </div>
                        <div class="about-view mt-2">
                            <p class="text-sm text-reddit-secondary dark:text-reddit-darkText leading-relaxed" id="profile-about"><c:out value="${userList.get(2)}" escapeXml="true" default="Passionate developer who loves programming." /></p>
                        </div>
                        <div class="about-edit-wrap mt-3">
                            <form method="post" action="UpdateAboutServlet">
                                <textarea name="about" rows="3" placeholder="Short bio..." class="w-full rounded-md border border-reddit-border dark:border-reddit-darkBorder dark:bg-reddit-darkBg dark:text-reddit-darkText px-3 py-2 text-sm focus:ring-1 focus:ring-reddit-blue focus:border-reddit-blue outline-none"><c:out value="${userList.get(2)}" escapeXml="true" /></textarea>
                                <div class="flex gap-2 mt-2">
                                    <button type="submit" class="px-4 py-1.5 rounded-full bg-reddit-orange text-white text-xs font-bold hover:bg-reddit-orangeHover transition-colors">Save</button>
                                    <label for="about-edit-toggle" class="px-4 py-1.5 rounded-full border border-reddit-border dark:border-reddit-darkBorder text-xs font-medium text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">Cancel</label>
                                </div>
                            </form>
                        </div>

                        <div class="grid grid-cols-3 gap-3 mt-4 pt-4 border-t border-reddit-border dark:border-reddit-darkBorder text-center">
                            <div class="py-2">
                                <p class="text-xl font-bold text-reddit-text dark:text-reddit-darkText"><c:out value="${snsPostCount}" escapeXml="true" /></p>
                                <p class="text-[11px] text-reddit-muted font-medium uppercase tracking-wide">Posts</p>
                            </div>
                            <div class="py-2">
                                <p class="text-xl font-bold text-reddit-text dark:text-reddit-darkText"><c:out value="${questionsCount}" escapeXml="true" /></p>
                                <p class="text-[11px] text-reddit-muted font-medium uppercase tracking-wide">Questions</p>
                            </div>
                            <div class="py-2">
                                <p class="text-xl font-bold text-reddit-text dark:text-reddit-darkText"><c:out value="${answerCount}" escapeXml="true" /></p>
                                <p class="text-[11px] text-reddit-muted font-medium uppercase tracking-wide">Answers</p>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Tabs -->
                <div class="bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder transition-colors">
                    <div class="border-b border-reddit-border dark:border-reddit-darkBorder flex">
                        <button type="button" data-tab="snsposts" class="tab-btn flex-1 py-3 px-4 text-xs font-bold uppercase tracking-wide text-reddit-orange border-b-2 border-reddit-orange bg-reddit-hover dark:bg-reddit-darkHover transition-colors">SNS Posts</button>
                        <button type="button" data-tab="questions" class="tab-btn flex-1 py-3 px-4 text-xs font-bold uppercase tracking-wide text-reddit-secondary dark:text-reddit-darkText border-b-2 border-transparent hover:text-reddit-text dark:hover:text-white transition-colors">Questions</button>
                        <button type="button" data-tab="answers" class="tab-btn flex-1 py-3 px-4 text-xs font-bold uppercase tracking-wide text-reddit-secondary dark:text-reddit-darkText border-b-2 border-transparent hover:text-reddit-text dark:hover:text-white transition-colors">Answers</button>
                    </div>
                    <div class="p-3">
                        <div id="snsposts" class="tab-pane active space-y-1">
                            <c:choose>
                                <c:when test="${empty myPosts}"><p class="text-reddit-muted text-sm py-6 text-center">No SNS posts yet.</p></c:when>
                                <c:otherwise>
                                    <c:forEach var="post" items="${myPosts}">
                                        <a href="SNSDetail?pid=${post.pid}" class="flex items-center gap-3 p-3 rounded-md hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors border border-transparent hover:border-reddit-border dark:hover:border-reddit-darkBorder">
                                            <div class="w-8 h-8 rounded-md bg-reddit-bg dark:bg-reddit-darkBg flex items-center justify-center flex-shrink-0"><i class="far fa-comment-alt text-reddit-muted text-xs"></i></div>
                                            <div class="flex-1 min-w-0">
                                                <p class="text-sm font-medium text-reddit-text dark:text-reddit-darkText line-clamp-1"><c:out value="${post.pbody}" escapeXml="true" /></p>
                                                <span class="text-[11px] text-reddit-muted"><c:out value="${post.pcreatedAt}" escapeXml="true" /></span>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div id="questions" class="tab-pane space-y-1">
                            <c:choose>
                                <c:when test="${empty myQuestions}"><p class="text-reddit-muted text-sm py-6 text-center">No questions yet.</p></c:when>
                                <c:otherwise>
                                    <c:forEach var="q" items="${myQuestions}">
                                        <a href="QADetail?qid=${q.qid}" class="flex items-center gap-3 p-3 rounded-md hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors border border-transparent hover:border-reddit-border dark:hover:border-reddit-darkBorder">
                                            <div class="w-8 h-8 rounded-md bg-reddit-bg dark:bg-reddit-darkBg flex items-center justify-center flex-shrink-0"><i class="fas fa-question text-reddit-orange text-xs"></i></div>
                                            <div class="flex-1 min-w-0">
                                                <p class="text-sm font-medium text-reddit-text dark:text-reddit-darkText"><c:out value="${q.title}" escapeXml="true" /></p>
                                                <span class="text-[11px] text-reddit-muted"><c:out value="${q.subjectName}" escapeXml="true" /> &middot; <c:out value="${q.qcreatedAt}" escapeXml="true" /></span>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div id="answers" class="tab-pane space-y-1">
                            <c:choose>
                                <c:when test="${empty myAnswers}"><p class="text-reddit-muted text-sm py-6 text-center">No answers yet.</p></c:when>
                                <c:otherwise>
                                    <c:forEach var="ans" items="${myAnswers}">
                                        <a href="QADetail?qid=${ans.questionId}" class="flex items-center gap-3 p-3 rounded-md hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors border border-transparent hover:border-reddit-border dark:hover:border-reddit-darkBorder">
                                            <div class="w-8 h-8 rounded-md bg-reddit-bg dark:bg-reddit-darkBg flex items-center justify-center flex-shrink-0"><i class="fas fa-reply text-reddit-blue text-xs"></i></div>
                                            <div class="flex-1 min-w-0">
                                                <p class="text-sm font-medium text-reddit-text dark:text-reddit-darkText">Answered: <c:out value="${ans.questionTitle}" escapeXml="true" /></p>
                                                <p class="text-xs text-reddit-secondary dark:text-reddit-darkText line-clamp-1 mt-0.5"><c:out value="${ans.answerBody}" escapeXml="true" /></p>
                                                <span class="text-[11px] text-reddit-muted"><c:out value="${ans.answerCreatedAt}" escapeXml="true" /></span>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <c:if test="${not empty sessionScope.errortext}">
    <script>Swal.fire({icon:'error',title:'Oops!',text:'${sessionScope.errortext}',confirmButtonColor:'#FF4500'});</script>
    <c:remove var="errortext" scope="session"/>
    </c:if>
    <c:if test="${not empty message}">
    <script>Swal.fire({icon:'error',title:'Error!',text:'${message}',confirmButtonColor:'#FF4500'});</script>
    </c:if>

    <script>
        document.querySelectorAll('.tab-btn').forEach(function(btn) {
            btn.addEventListener('click', function() {
                var id = this.dataset.tab;
                document.querySelectorAll('.tab-btn').forEach(function(b) {
                    b.classList.remove('text-reddit-orange', 'border-reddit-orange', 'bg-reddit-hover', 'dark:bg-reddit-darkHover');
                    b.classList.add('text-reddit-secondary', 'dark:text-reddit-darkText', 'border-transparent');
                });
                this.classList.add('text-reddit-orange', 'border-reddit-orange', 'bg-reddit-hover', 'dark:bg-reddit-darkHover');
                this.classList.remove('text-reddit-secondary', 'dark:text-reddit-darkText', 'border-transparent');
                document.querySelectorAll('.tab-pane').forEach(function(p) { p.classList.remove('active'); });
                document.getElementById(id).classList.add('active');
            });
        });
    </script>
</body>
</html>
