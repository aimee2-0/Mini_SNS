<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Post — Guys Into Code</title>
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
                    <form method="post" action="ProfileServlet" class="inline">
                        <button type="submit" class="px-3 py-1.5 rounded-full text-reddit-secondary dark:text-reddit-darkText text-sm hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors"><i class="far fa-user mr-1"></i>Profile</button>
                    </form>
                </div>
            </div>
        </div>
    </header>

    <main class="max-w-[740px] mx-auto px-4 py-5">
        <!-- Header -->
        <div class="flex items-center justify-between mb-3">
            <h1 class="text-lg font-bold text-reddit-text dark:text-reddit-darkText">Create a post</h1>
            <span class="text-xs text-reddit-muted">Draft</span>
        </div>
        <div class="h-px bg-reddit-border dark:bg-reddit-darkBorder mb-4"></div>

        <!-- Form Card -->
        <div class="bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder overflow-hidden transition-colors">
            <form id="addPostForm" method="post" action="SNSPostServlet" enctype="multipart/form-data">
                <div class="p-4 space-y-4">
                    <div class="flex items-center gap-3">
                        <span class="w-10 h-10 rounded-full overflow-hidden bg-gray-200 dark:bg-reddit-darkBorder flex-shrink-0 inline-flex items-center justify-center">
                            <img src="ProfileImage" alt="" class="w-10 h-10 object-cover" onerror="this.parentElement.innerHTML='<i class=\'fas fa-user text-gray-400\'></i>'">
                        </span>
                        <div>
                            <strong id="userName" class="text-sm text-reddit-text dark:text-reddit-darkText">Your Name</strong>
                            <p class="text-[11px] text-reddit-muted">Posting now</p>
                        </div>
                    </div>
                    <div>
                        <textarea name="body" id="postContent" rows="6" placeholder="What's on your mind?" required class="w-full px-3 py-2.5 rounded-md border border-reddit-border dark:border-reddit-darkBorder bg-reddit-card dark:bg-reddit-darkCard text-reddit-text dark:text-reddit-darkText text-sm focus:ring-1 focus:ring-reddit-blue focus:border-reddit-blue outline-none resize-y transition-colors placeholder:text-reddit-muted"></textarea>
                    </div>
                    <div>
                        <label class="flex items-center gap-2 px-3 py-2 rounded-md border border-dashed border-reddit-border dark:border-reddit-darkBorder hover:border-reddit-blue transition-colors text-sm text-reddit-muted">
                            <i class="far fa-image"></i> Add image (optional)
                            <input type="file" id="postImage" name="postImage" accept="image/*" class="hidden">
                        </label>
                        <div id="imagePreviewContainer" class="mt-2 relative inline-block hidden">
                            <img id="imagePreview" class="max-h-40 rounded-md border border-reddit-border dark:border-reddit-darkBorder" alt="Preview">
                            <button type="button" id="removeImage" class="absolute top-1 right-1 w-6 h-6 rounded-full bg-black/70 text-white flex items-center justify-center hover:bg-black text-xs"><i class="fas fa-times"></i></button>
                        </div>
                    </div>
                </div>
                <div class="flex items-center justify-between px-4 py-3 bg-reddit-hover dark:bg-[#151516] border-t border-reddit-border dark:border-reddit-darkBorder">
                    <a href="Top" class="px-4 py-1.5 rounded-full border border-reddit-border dark:border-reddit-darkBorder text-reddit-secondary dark:text-reddit-darkText text-xs font-bold hover:bg-reddit-card dark:hover:bg-reddit-darkCard transition-colors">Cancel</a>
                    <div class="flex gap-2">
                        <button type="button" id="previewBtn" class="px-4 py-1.5 rounded-full border border-reddit-border dark:border-reddit-darkBorder text-reddit-secondary dark:text-reddit-darkText text-xs font-bold hover:bg-reddit-card dark:hover:bg-reddit-darkCard transition-colors">Preview</button>
                        <button type="submit" class="px-4 py-1.5 rounded-full bg-reddit-orange text-white text-xs font-bold hover:bg-reddit-orangeHover transition-colors">Post</button>
                    </div>
                </div>
            </form>
        </div>

        <!-- Preview -->
        <div id="previewCard" class="mt-4 bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder overflow-hidden hidden transition-colors">
            <div class="px-4 py-3 border-b border-reddit-border dark:border-reddit-darkBorder text-xs font-bold text-reddit-secondary dark:text-reddit-darkText uppercase tracking-wide">Preview</div>
            <div class="p-4">
                <div class="flex items-center gap-2.5 mb-3">
                    <span class="w-8 h-8 rounded-full overflow-hidden bg-gray-200 dark:bg-reddit-darkBorder inline-flex items-center justify-center">
                        <img src="ProfileImage" alt="" class="w-8 h-8 object-cover" onerror="this.parentElement.innerHTML='<i class=\'fas fa-user text-gray-400 text-xs\'></i>'">
                    </span>
                    <div>
                        <strong id="previewUserName" class="text-xs text-reddit-text dark:text-reddit-darkText">Your Name</strong>
                        <p class="text-[10px] text-reddit-muted">Just now</p>
                    </div>
                </div>
                <p id="previewContent" class="text-sm text-reddit-text dark:text-reddit-darkText mb-3"></p>
                <div id="previewImageContainer" class="hidden"><img id="previewImage" class="rounded-md max-h-48 object-contain bg-black/5 dark:bg-white/5" alt="Post"></div>
            </div>
        </div>
    </main>

    <c:if test="${not empty message}">
    <script>Swal.fire({icon:'error',title:'Error!',text:'${message}',confirmButtonColor:'#FF4500'});</script>
    </c:if>

    <footer class="bg-reddit-card dark:bg-reddit-darkCard border-t border-reddit-border dark:border-reddit-darkBorder text-reddit-muted text-center py-3 text-xs mt-8 transition-colors">&copy; 2026 Guys Into Code</footer>

    <script>
        document.getElementById('postImage').addEventListener('change', function(e) {
            var preview = document.getElementById('imagePreview');
            var container = document.getElementById('imagePreviewContainer');
            if (this.files && this.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) { preview.src = e.target.result; container.classList.remove('hidden'); };
                reader.readAsDataURL(this.files[0]);
            } else { container.classList.add('hidden'); }
        });
        document.getElementById('removeImage').addEventListener('click', function() {
            document.getElementById('postImage').value = '';
            document.getElementById('imagePreviewContainer').classList.add('hidden');
        });
        document.getElementById('previewBtn').addEventListener('click', function() {
            var card = document.getElementById('previewCard');
            document.getElementById('previewContent').textContent = document.getElementById('postContent').value;
            var img = document.getElementById('postImage').files[0];
            var previewImg = document.getElementById('previewImage');
            var previewImgContainer = document.getElementById('previewImageContainer');
            if (img) {
                var r = new FileReader();
                r.onload = function(e) { previewImg.src = e.target.result; previewImgContainer.classList.remove('hidden'); };
                r.readAsDataURL(img);
            } else { previewImgContainer.classList.add('hidden'); }
            card.classList.toggle('hidden');
        });
    </script>
</body>
</html>
