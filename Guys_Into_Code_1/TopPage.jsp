<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Guys Into Code</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js/theme-toggle.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
      tailwind.config = { darkMode: 'class',
        theme: {
          extend: {
            fontFamily: { sans: ['IBM Plex Sans', '-apple-system', 'BlinkMacSystemFont', 'Segoe UI', 'Roboto', 'sans-serif'] },
            colors: {
              reddit: { orange: '#FF4500', orangeHover: '#cc3700', blue: '#0079D3', bg: '#DAE0E6', card: '#ffffff', border: '#EDEFF1', text: '#1c1c1c', secondary: '#787c7e', muted: '#a8aaab', hover: '#f6f7f8', nav: '#ffffff', darkBg: '#030303', darkCard: '#1a1a1b', darkBorder: '#343536', darkText: '#d7dadc', darkHover: '#2d2d2e' }
            }
          }
        }
      }
    </script>
    <style type="text/tailwindcss">
      @layer utilities { .scrollbar-thin { scrollbar-width: thin; } }
    </style>
</head>
<body class="bg-reddit-bg dark:bg-reddit-darkBg text-reddit-text dark:text-reddit-darkText font-sans min-h-screen flex flex-col transition-colors">

    <!-- Reddit-style Navbar -->
    <header class="sticky top-0 z-50 bg-reddit-nav dark:bg-reddit-darkCard border-b border-reddit-border dark:border-reddit-darkBorder shadow-sm transition-colors">
        <div class="max-w-[1200px] mx-auto px-4">
            <div class="flex items-center justify-between h-12">
                <a href="Top" class="flex items-center gap-2 hover:opacity-80 transition-opacity">
                    <div class="w-8 h-8 rounded-full bg-reddit-orange flex items-center justify-center text-white text-sm">
                        <i class="fas fa-code"></i>
                    </div>
                    <span class="text-lg font-bold text-reddit-text dark:text-reddit-darkText hidden sm:block">Guys<span class="text-reddit-orange">Into</span>Code</span>
                </a>
                <div class="flex items-center gap-2">
                    <a href="QAPost" class="hidden sm:inline-flex items-center gap-1.5 px-3 py-1.5 rounded-full border border-reddit-border dark:border-reddit-darkBorder text-reddit-secondary dark:text-reddit-darkText text-sm font-medium hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">
                        <i class="fas fa-plus text-xs"></i> Ask
                    </a>
                    <a href="SNSPost" class="hidden sm:inline-flex items-center gap-1.5 px-3 py-1.5 rounded-full border border-reddit-border dark:border-reddit-darkBorder text-reddit-secondary dark:text-reddit-darkText text-sm font-medium hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">
                        <i class="fas fa-plus text-xs"></i> Post
                    </a>
                    <form method="post" action="ProfileServlet" class="inline">
                        <button type="submit" class="inline-flex items-center gap-1.5 px-3 py-1.5 rounded-full text-reddit-secondary dark:text-reddit-darkText text-sm hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">
                            <i class="far fa-user"></i><span class="hidden sm:inline">Profile</span>
                        </button>
                    </form>
                    <form method="post" action="Logout" class="inline">
                        <button type="submit" class="inline-flex items-center gap-1.5 px-3 py-1.5 rounded-full text-reddit-secondary dark:text-reddit-darkText text-sm hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">
                            <i class="fas fa-sign-out-alt"></i><span class="hidden sm:inline">Logout</span>
                        </button>
                    </form>
                    <button type="button" onclick="themeToggle()" class="w-8 h-8 rounded-full flex items-center justify-center text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors" title="Toggle theme">
                        <i class="fas fa-moon dark:hidden"></i><i class="fas fa-sun hidden dark:inline"></i>
                    </button>
                </div>
            </div>
        </div>
    </header>

    <div class="flex-1 flex max-w-[1200px] mx-auto w-full gap-5 p-4 lg:pt-5">

        <!-- Left Sidebar - Topics -->
        <aside class="hidden lg:block w-[220px] flex-shrink-0 sticky top-16 self-start">
            <div class="bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder overflow-hidden transition-colors">
                <div class="px-4 py-3 bg-reddit-orange text-white text-xs font-bold uppercase tracking-wider">
                    <i class="fas fa-fire mr-1"></i> Topics
                </div>
                <div class="py-1">
                    <a href="#" class="topic-link flex items-center gap-3 px-4 py-2 text-sm font-medium bg-reddit-hover dark:bg-reddit-darkHover text-reddit-text dark:text-reddit-darkText transition-colors" data-topic="all">
                        <i class="fas fa-globe-americas text-reddit-blue w-5 text-center"></i> All Topics
                    </a>
                    <a href="#" class="topic-link flex items-center gap-3 px-4 py-2 text-sm text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors" data-topic="javascript">
                        <span class="w-5 h-5 rounded-full bg-yellow-400 flex items-center justify-center text-white text-[10px] font-bold">JS</span> JavaScript
                    </a>
                    <a href="#" class="topic-link flex items-center gap-3 px-4 py-2 text-sm text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors" data-topic="java">
                        <span class="w-5 h-5 rounded-full bg-red-500 flex items-center justify-center text-white text-[10px] font-bold">JA</span> Java
                    </a>
                    <a href="#" class="topic-link flex items-center gap-3 px-4 py-2 text-sm text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors" data-topic="c">
                        <span class="w-5 h-5 rounded-full bg-blue-600 flex items-center justify-center text-white text-[10px] font-bold">C</span> C
                    </a>
                    <a href="#" class="topic-link flex items-center gap-3 px-4 py-2 text-sm text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors" data-topic="c#">
                        <span class="w-5 h-5 rounded-full bg-purple-600 flex items-center justify-center text-white text-[10px] font-bold">C#</span> C#
                    </a>
                    <a href="#" class="topic-link flex items-center gap-3 px-4 py-2 text-sm text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors" data-topic="html">
                        <span class="w-5 h-5 rounded-full bg-orange-500 flex items-center justify-center text-white text-[10px] font-bold">H</span> HTML
                    </a>
                    <a href="#" class="topic-link flex items-center gap-3 px-4 py-2 text-sm text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors" data-topic="css">
                        <span class="w-5 h-5 rounded-full bg-blue-500 flex items-center justify-center text-white text-[10px] font-bold">CS</span> CSS
                    </a>
                    <a href="#" class="topic-link flex items-center gap-3 px-4 py-2 text-sm text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors" data-topic="oracle">
                        <span class="w-5 h-5 rounded-full bg-red-600 flex items-center justify-center text-white text-[10px] font-bold">OR</span> Oracle
                    </a>
                </div>
            </div>
        </aside>

        <!-- Main Q&A Feed -->
        <main class="flex-1 min-w-0 space-y-3 overflow-y-auto scrollbar-thin pb-8">
            <!-- Create post bar -->
            <div class="bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder p-2 flex items-center gap-3 transition-colors">
                <span class="w-9 h-9 rounded-full overflow-hidden bg-gray-200 dark:bg-reddit-darkBorder flex-shrink-0 inline-flex items-center justify-center">
                    <img src="ProfileImage" alt="" class="w-9 h-9 object-cover" onerror="this.parentElement.innerHTML='<i class=\'fas fa-user text-gray-400\'></i>'">
                </span>
                <a href="QAPost" class="flex-1 bg-reddit-bg dark:bg-reddit-darkBg border border-reddit-border dark:border-reddit-darkBorder rounded-md px-4 py-2 text-sm text-reddit-muted hover:bg-white dark:hover:bg-reddit-darkHover hover:border-reddit-blue transition-colors">
                    Ask a Question...
                </a>
            </div>

            <!-- Feed header -->
            <div class="bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder px-4 py-2 flex items-center justify-between transition-colors">
                <div class="flex items-center gap-3">
                    <span class="flex items-center gap-1.5 text-xs font-bold text-reddit-orange">
                        <i class="fas fa-fire"></i> Q&A Feed
                    </span>
                    <span class="text-xs text-reddit-muted">|</span>
                    <span class="text-xs text-reddit-muted"><c:out value="${qaCount}" escapeXml="true" /> questions</span>
                </div>
            </div>

            <!-- Q&A Posts -->
            <c:forEach var="QA" items="${QATimeline}">
            <div class="qa-topic bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder overflow-hidden hover:border-reddit-secondary dark:hover:border-reddit-darkText transition-colors" data-topic="${fn:toLowerCase(QA.subjectName)}">
                <div class="flex">
                    <!-- Left accent bar -->
                    <div class="w-10 bg-reddit-hover dark:bg-[#151516] flex-shrink-0 flex flex-col items-center pt-3 gap-1">
                        <i class="fas fa-comment-alt text-reddit-muted text-xs"></i>
                        <span class="text-[11px] font-bold text-reddit-secondary dark:text-reddit-darkText"><c:out value="${QA.answersList.size()}" escapeXml="true" /></span>
                    </div>
                    <!-- Content -->
                    <div class="flex-1 min-w-0 p-3 pb-1">
                        <div class="flex items-center gap-1.5 text-xs mb-1.5">
                            <span class="font-bold text-reddit-text dark:text-reddit-darkText"><c:out value="${QA.subjectName}" escapeXml="true" /></span>
                            <span class="text-reddit-muted">·</span>
                            <span class="text-reddit-muted">Posted by</span>
                            <form method="post" action="OtherProfileServlet" class="inline">
                                <input type="hidden" name="userName" value="${QA.userName}">
                                <input type="hidden" name="user_id" value="${QA.userId}">
                                <button type="submit" class="text-reddit-muted hover:underline"><c:out value="${QA.userName}" escapeXml="true" /></button>
                            </form>
                            <span class="text-reddit-muted">· <c:out value="${QA.qcreatedAt}" escapeXml="true" /></span>
                        </div>
                        <h2 class="text-lg font-semibold text-reddit-text dark:text-reddit-darkText mb-1.5 leading-snug">
                            <a href="QADetail?qid=${QA.qid}" class="hover:underline"><c:out value="${QA.title}" escapeXml="true" /></a>
                        </h2>
                        <p class="text-sm text-reddit-secondary dark:text-[#9ca3af] leading-relaxed mb-2 line-clamp-3"><c:out value="${QA.qbody}" escapeXml="true" /></p>
                        <div class="mb-2">
                            <a href="QADetail?qid=${QA.qid}">
                                <img src="QAPostImage?qid=${QA.qid}" alt="" class="w-full rounded-md max-h-[400px] object-contain bg-black/5 dark:bg-white/5" onerror="this.parentElement.style.display='none'">
                            </a>
                        </div>
                        <!-- Action bar -->
                        <div class="flex items-center gap-1 -ml-1 mb-1">
                            <button type="button" class="comment-toggle flex items-center gap-1.5 px-2 py-1.5 rounded-sm text-xs font-bold text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">
                                <i class="far fa-comment-alt"></i> <c:out value="${QA.answersList.size()}" escapeXml="true" /> Answers
                            </button>
                            <a href="QADetail?qid=${QA.qid}" class="flex items-center gap-1.5 px-2 py-1.5 rounded-sm text-xs font-bold text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">
                                <i class="fas fa-expand-alt"></i> Full View
                            </a>
                        </div>
                    </div>
                </div>
                <!-- Comments dropdown -->
                <div class="qa-comments-dropdown border-t border-reddit-border dark:border-reddit-darkBorder bg-reddit-hover dark:bg-[#151516] px-4 py-3 hidden">
                    <div class="space-y-3 mb-3 ml-6">
                        <c:forEach var="answer" items="${QA.answersList}">
                        <div class="flex gap-2.5">
                            <span class="w-7 h-7 rounded-full overflow-hidden bg-gray-200 dark:bg-reddit-darkBorder flex-shrink-0 inline-flex items-center justify-center">
                                <img src="UserImage?user_id=${answer.userId}" alt="" class="w-7 h-7 object-cover" onerror="this.parentElement.innerHTML='<i class=\'fas fa-user text-gray-400 text-xs\'></i>'">
                            </span>
                            <div class="flex-1 min-w-0">
                                <div class="flex items-center gap-1.5 text-xs mb-0.5">
                                    <form method="post" action="OtherProfileServlet" class="inline">
                                        <input type="hidden" name="userName" value="${answer.userName}">
                                        <input type="hidden" name="user_id" value="${answer.userId}">
                                        <button type="submit" class="font-bold text-reddit-text dark:text-reddit-darkText hover:underline"><c:out value="${answer.userName}" escapeXml="true" /></button>
                                    </form>
                                    <span class="text-reddit-muted">· <c:out value="${answer.acreatedAt}" escapeXml="true" /></span>
                                </div>
                                <p class="text-sm text-reddit-text dark:text-reddit-darkText leading-relaxed"><c:out value="${answer.abody}" escapeXml="true" /></p>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                    <form method="post" action="./AnswerServlet" class="flex items-center gap-2 ml-6">
                        <span class="w-7 h-7 rounded-full overflow-hidden bg-gray-200 dark:bg-reddit-darkBorder flex-shrink-0 inline-flex items-center justify-center">
                            <img src="ProfileImage" alt="" class="w-7 h-7 object-cover" onerror="this.parentElement.innerHTML='<i class=\'fas fa-user text-gray-400 text-xs\'></i>'">
                        </span>
                        <input type="text" name="comment" placeholder="What are your thoughts?" class="flex-1 rounded-md border border-reddit-border dark:border-reddit-darkBorder dark:bg-reddit-darkBg dark:text-reddit-darkText px-3 py-1.5 text-sm focus:ring-1 focus:ring-reddit-blue focus:border-reddit-blue outline-none transition-colors">
                        <input type="hidden" name="question_id" value="${QA.qid}">
                        <button type="submit" class="px-3 py-1.5 rounded-full bg-reddit-orange text-white text-xs font-bold hover:bg-reddit-orangeHover transition-colors">Reply</button>
                    </form>
                </div>
            </div>
            </c:forEach>
        </main>

        <!-- Right Sidebar - SNS Feed -->
        <aside class="hidden xl:block w-[312px] flex-shrink-0 sticky top-16 self-start overflow-y-auto scrollbar-thin max-h-[calc(100vh-5rem)]">
            <!-- SNS Posts Section -->
            <div class="bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder overflow-hidden transition-colors">
                <div class="relative">
                    <div class="h-8 bg-reddit-orange"></div>
                    <div class="px-3 pb-3 pt-2">
                        <div class="flex items-center justify-between mb-2">
                            <div class="flex items-center gap-2">
                                <h2 class="text-base font-bold text-reddit-text dark:text-reddit-darkText">SNS Feed</h2>
                                <span class="text-xs font-medium bg-reddit-orange text-white px-1.5 py-0.5 rounded-sm"><c:out value="${snsCount}" escapeXml="true" /></span>
                            </div>
                            <a href="SNSPost" class="px-3 py-1 rounded-full bg-reddit-orange text-white text-xs font-bold hover:bg-reddit-orangeHover transition-colors">
                                + Post
                            </a>
                        </div>
                        <p class="text-xs text-reddit-muted">Latest posts from the community</p>
                    </div>
                </div>
                <div class="divide-y divide-reddit-border dark:divide-reddit-darkBorder">
                    <c:forEach var="post" items="${SNSTimeline}">
                    <div class="p-3 hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">
                        <div class="flex items-center gap-2.5 mb-2">
                            <span class="w-8 h-8 rounded-full overflow-hidden bg-gray-200 dark:bg-reddit-darkBorder flex-shrink-0 inline-flex items-center justify-center">
                                <img src="UserImage?user_id=${post.uid}" alt="" class="w-8 h-8 object-cover" onerror="this.parentElement.innerHTML='<i class=\'fas fa-user text-gray-400 text-xs\'></i>'">
                            </span>
                            <div class="min-w-0 flex-1">
                                <form method="post" action="OtherProfileServlet" class="inline">
                                    <input type="hidden" name="userName" value="${post.userName}">
                                    <input type="hidden" name="user_id" value="${post.uid}">
                                    <button type="submit" class="text-xs font-bold text-reddit-text dark:text-reddit-darkText hover:underline block truncate"><c:out value="${post.userName}" escapeXml="true" /></button>
                                </form>
                                <span class="text-[11px] text-reddit-muted"><c:out value="${post.pcreatedAt}" escapeXml="true" /></span>
                            </div>
                        </div>
                        <a href="SNSDetail?pid=${post.pid}" class="block text-sm text-reddit-text dark:text-reddit-darkText leading-relaxed hover:underline mb-2 line-clamp-3"><c:out value="${post.pbody}" escapeXml="true" /></a>
                        <div class="mb-2">
                            <a href="SNSDetail?pid=${post.pid}">
                                <img src="SNSPostImage?post_id=${post.pid}" alt="" class="w-full rounded-md max-h-40 object-contain bg-black/5 dark:bg-white/5" onerror="this.parentElement.style.display='none'">
                            </a>
                        </div>
                        <div class="sns-comment-area">
                            <button type="button" class="sns-comment-toggle flex items-center gap-1 text-xs font-bold text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover px-1.5 py-1 rounded-sm transition-colors">
                                <i class="far fa-comment-alt"></i> <c:out value="${post.commentsList.size()}" escapeXml="true" /> Comments
                            </button>
                            <div class="sns-comments-dropdown mt-2 hidden">
                                <div class="space-y-2 mb-2">
                                    <c:forEach var="com" items="${post.commentsList}">
                                    <div class="flex gap-2">
                                        <span class="w-5 h-5 rounded-full overflow-hidden bg-gray-200 dark:bg-reddit-darkBorder flex-shrink-0 inline-flex items-center justify-center">
                                            <img src="UserImage?user_id=${com.userId}" alt="" class="w-5 h-5 object-cover" onerror="this.parentElement.innerHTML='<i class=\'fas fa-user text-gray-400 text-[8px]\'></i>'">
                                        </span>
                                        <div class="flex-1 min-w-0 text-xs">
                                            <form method="post" action="OtherProfileServlet" class="inline">
                                                <input type="hidden" name="userName" value="${com.userName}">
                                                <input type="hidden" name="user_id" value="${com.userId}">
                                                <button type="submit" class="font-bold text-reddit-text dark:text-reddit-darkText hover:underline"><c:out value="${com.userName}" escapeXml="true" /></button>
                                            </form>
                                            <span class="text-reddit-text dark:text-reddit-darkText"><c:out value="${com.cbody}" escapeXml="true" /></span>
                                            <span class="text-reddit-muted text-[10px] block"><c:out value="${com.ccreatedAt}" escapeXml="true" /></span>
                                        </div>
                                    </div>
                                    </c:forEach>
                                </div>
                                <form method="post" action="./CommentServlet" class="flex items-center gap-1.5">
                                    <input type="hidden" name="post_id" value="${post.pid}">
                                    <input type="text" name="comment" placeholder="Add a comment..." class="flex-1 rounded-md border border-reddit-border dark:border-reddit-darkBorder dark:bg-reddit-darkBg dark:text-reddit-darkText px-2.5 py-1 text-xs focus:ring-1 focus:ring-reddit-blue focus:border-reddit-blue outline-none transition-colors">
                                    <button type="submit" class="text-reddit-orange text-xs font-bold hover:text-reddit-orangeHover transition-colors">Post</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </aside>
    </div>

    <footer class="bg-reddit-card dark:bg-reddit-darkCard border-t border-reddit-border dark:border-reddit-darkBorder text-reddit-muted text-center py-3 text-xs transition-colors">
        &copy; 2026 Guys Into Code
    </footer>

    <c:if test="${not empty sessionScope.errortext}">
    <script>Swal.fire({icon:'error',title:'Oops!',text:'${sessionScope.errortext}',confirmButtonColor:'#FF4500'});</script>
    <c:remove var="errortext" scope="session"/>
    </c:if>
    <c:if test="${not empty message}">
    <script>Swal.fire({icon:'error',title:'Error!',text:'${message}',confirmButtonColor:'#FF4500'});</script>
    </c:if>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            document.querySelectorAll('.topic-link').forEach(function (link) {
                link.addEventListener('click', function (e) {
                    e.preventDefault();
                    var topic = this.dataset.topic.toLowerCase();
                    document.querySelectorAll('.topic-link').forEach(function (l) {
                        l.classList.remove('bg-reddit-hover', 'dark:bg-reddit-darkHover', 'font-medium');
                    });
                    this.classList.add('bg-reddit-hover', 'dark:bg-reddit-darkHover', 'font-medium');
                    document.querySelectorAll('.qa-topic').forEach(function (el) {
                        el.style.display = (topic === 'all' || (el.dataset.topic || '').toLowerCase() === topic) ? '' : 'none';
                    });
                });
            });
            document.querySelectorAll('.comment-toggle').forEach(function (btn) {
                btn.addEventListener('click', function () {
                    var card = this.closest('.qa-topic');
                    var drop = card.querySelector('.qa-comments-dropdown');
                    drop.classList.toggle('hidden');
                });
            });
            document.querySelectorAll('.sns-comment-toggle').forEach(function (btn) {
                btn.addEventListener('click', function () {
                    var drop = this.closest('.sns-comment-area').querySelector('.sns-comments-dropdown');
                    if (drop) drop.classList.toggle('hidden');
                });
            });
        });
    </script>
</body>
</html>
