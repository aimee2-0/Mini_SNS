<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Post — Guys Into Code</title>
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
            fontFamily: { sans: ['-apple-system', 'BlinkMacSystemFont', 'Segoe UI', 'Roboto', 'sans-serif'] },
            colors: {
              reddit: { orange: '#FF4500', orangeHover: '#cc3700', blue: '#0079D3', bg: '#DAE0E6', card: '#ffffff', border: '#EDEFF1', text: '#1c1c1c', secondary: '#787c7e', muted: '#a8aaab', hover: '#f6f7f8', darkBg: '#030303', darkCard: '#1a1a1b', darkBorder: '#343536', darkText: '#d7dadc', darkHover: '#2d2d2e' }
            }
          }
        }
      }
    </script>
</head>
<body class="bg-reddit-bg dark:bg-reddit-darkBg text-reddit-text dark:text-reddit-darkText font-sans min-h-screen transition-colors">
    <!-- Navbar -->
    <header class="sticky top-0 z-40 bg-reddit-card dark:bg-reddit-darkCard border-b border-reddit-border dark:border-reddit-darkBorder shadow-sm transition-colors">
        <div class="max-w-[740px] mx-auto px-4">
            <div class="flex items-center justify-between h-12">
                <a href="Top" class="flex items-center gap-2 hover:opacity-80 transition-opacity">
                    <div class="w-8 h-8 rounded-full bg-reddit-orange flex items-center justify-center text-white text-sm"><i class="fas fa-code"></i></div>
                    <span class="text-lg font-bold text-reddit-text dark:text-reddit-darkText hidden sm:block">Guys<span class="text-reddit-orange">Into</span>Code</span>
                </a>
                <div class="flex items-center gap-2">
                    <button type="button" onclick="themeToggle()" class="w-8 h-8 rounded-full flex items-center justify-center text-reddit-secondary dark:text-reddit-darkText hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors" title="Toggle theme"><i class="fas fa-moon dark:hidden"></i><i class="fas fa-sun hidden dark:inline"></i></button>
                    <a href="Top" class="px-3 py-1.5 rounded-full border border-reddit-border dark:border-reddit-darkBorder text-reddit-secondary dark:text-reddit-darkText text-sm font-medium hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">Back</a>
                </div>
            </div>
        </div>
    </header>

    <c:if test="${not empty message}">
    <script>Swal.fire({icon:'error',title:'Error',text:'${message}',confirmButtonColor:'#FF4500'});</script>
    <c:remove var="errortext" scope="session"/>
    </c:if>

    <main class="max-w-[740px] mx-auto px-4 py-5">
        <!-- Post Card -->
        <article class="bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder overflow-hidden mb-4 transition-colors">
            <div class="flex">
                <!-- Left accent bar -->
                <div class="w-10 bg-reddit-hover dark:bg-[#151516] flex-shrink-0 flex flex-col items-center pt-3 gap-1">
                    <i class="far fa-comment-alt text-reddit-muted text-xs"></i>
                    <span class="text-[11px] font-bold text-reddit-secondary dark:text-reddit-darkText"><c:out value="${post.commentsList.size()}" escapeXml="true" /></span>
                </div>
                <div class="flex-1 min-w-0 p-4">
                    <div class="flex items-center gap-2 mb-3">
                        <span class="w-9 h-9 rounded-full overflow-hidden bg-gray-200 dark:bg-reddit-darkBorder flex-shrink-0 inline-flex items-center justify-center">
                            <img src="UserImage?user_id=${post.uid}" alt="" class="w-9 h-9 object-cover" onerror="this.parentElement.innerHTML='<i class=\'fas fa-user text-gray-400 text-xs\'></i>'">
                        </span>
                        <div>
                            <p class="text-xs font-bold text-reddit-text dark:text-reddit-darkText">
                                
                            <form method="post" action="OtherProfileServlet" class="inline">
                                <input type="hidden" name="userName" value="${post.userName}">
                                <input type="hidden" name="user_id" value="${post.uid}">
                                <button type="submit" class="text-xs font-bold text-reddit-text dark:text-reddit-darkText hover:underline block truncate"><c:out value="${post.userName}" escapeXml="true" /></button>
                            </form>



                            </p>
                            <p class="text-[11px] text-reddit-muted"><c:out value="${post.pcreatedAt}" escapeXml="true" /></p>
                        </div>
                    </div>
                    <p class="text-sm text-reddit-text dark:text-reddit-darkText leading-relaxed whitespace-pre-wrap mb-3"><c:out value="${post.pbody}" escapeXml="true" /></p>
                    <div class="mb-2">
                        <img src="SNSPostImage?post_id=${post.pid}" alt="Post" class="w-full rounded-md max-h-[500px] object-contain bg-black/5 dark:bg-white/5" onerror="this.parentElement.style.display='none'">
                    </div>
                    <div class="flex items-center gap-1 text-xs font-bold text-reddit-secondary dark:text-reddit-darkText -ml-1">
                        <span class="flex items-center gap-1.5 px-2 py-1.5 rounded-sm hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors">
                            <i class="far fa-comment-alt"></i> <c:out value="${post.commentsList.size()}" escapeXml="true" /> Comments
                        </span>
                    </div>
                </div>
            </div>
        </article>

        <!-- Comments Section -->
        <div class="bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder overflow-hidden transition-colors">
            <!-- Add comment form -->
            <div class="p-4 border-b border-reddit-border dark:border-reddit-darkBorder">
                <p class="text-xs text-reddit-muted mb-2">Comment as <span class="text-reddit-text dark:text-reddit-darkText font-bold">you</span></p>
                <form method="post" action="./CommentServlet">
                    <input type="hidden" name="post_id" value="${post.pid}">
                    <input type="hidden" name="redirect" value="SNSDetail">
                    <div class="border border-reddit-border dark:border-reddit-darkBorder rounded-md focus-within:border-reddit-blue transition-colors">
                        <input type="text" name="comment" placeholder="What are your thoughts?" required class="w-full px-3 py-2.5 text-sm bg-transparent text-reddit-text dark:text-reddit-darkText outline-none placeholder:text-reddit-muted">
                    </div>
                    <div class="flex justify-end mt-2">
                        <button type="submit" class="px-4 py-1.5 rounded-full bg-reddit-orange text-white text-xs font-bold hover:bg-reddit-orangeHover transition-colors">Comment</button>
                    </div>
                </form>
            </div>

            <!-- Comments list -->
            <div class="p-4">
                <c:choose>
                    <c:when test="${empty post.commentsList}"><p class="text-reddit-muted text-sm text-center py-4">No comments yet. Be the first to comment.</p></c:when>
                    <c:otherwise>
                        <div class="space-y-0">
                            <c:forEach var="com" items="${post.commentsList}">
                            <div class="flex gap-2.5 py-3 border-t border-reddit-border dark:border-reddit-darkBorder first:border-t-0">
                                <span class="w-7 h-7 rounded-full overflow-hidden bg-gray-200 dark:bg-reddit-darkBorder flex-shrink-0 inline-flex items-center justify-center mt-0.5">
                                    <img src="UserImage?user_id=${com.userId}" alt="" class="w-7 h-7 object-cover" onerror="this.parentElement.innerHTML='<i class=\'fas fa-user text-gray-400 text-xs\'></i>'">
                                </span>
                                <div class="flex-1 min-w-0">
                                    <div class="flex items-center gap-1.5 text-xs mb-1">
                                        <span class="font-bold text-reddit-text dark:text-reddit-darkText">
                                            
                                            <form method="post" action="OtherProfileServlet" class="inline">
                                                <input type="hidden" name="userName" value="${com.userName}">
                                                <input type="hidden" name="user_id" value="${com.userId}">
                                                <button type="submit" class="text-xs font-bold text-reddit-text dark:text-reddit-darkText hover:underline block truncate"><c:out value="${com.userName}" escapeXml="true" /></button>
                                            </form>                                        
                                        </span>
                                        <span class="text-reddit-muted">· <c:out value="${com.ccreatedAt}" escapeXml="true" /></span>
                                    </div>
                                    <p class="text-sm text-reddit-text dark:text-reddit-darkText leading-relaxed"><c:out value="${com.cbody}" escapeXml="true" /></p>
                                </div>
                            </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>
</body>
</html>
