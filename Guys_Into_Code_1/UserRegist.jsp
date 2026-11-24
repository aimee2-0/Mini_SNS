<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign up — Guys Into Code</title>
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
<body class="min-h-screen bg-reddit-bg dark:bg-reddit-darkBg flex items-center justify-center p-4 font-sans transition-colors relative">
    <button type="button" onclick="themeToggle()" class="fixed top-4 right-4 z-50 w-9 h-9 rounded-full bg-reddit-card dark:bg-reddit-darkCard border border-reddit-border dark:border-reddit-darkBorder text-reddit-secondary dark:text-reddit-darkText flex items-center justify-center hover:bg-reddit-hover dark:hover:bg-reddit-darkHover transition-colors shadow-sm" title="Toggle theme">
        <i class="fas fa-moon dark:hidden text-sm"></i><i class="fas fa-sun hidden dark:inline text-sm"></i>
    </button>

    <div class="w-full max-w-[400px]">
        <!-- Logo -->
        <div class="text-center mb-6">
            <div class="w-12 h-12 rounded-full bg-reddit-orange flex items-center justify-center text-white text-xl mx-auto mb-3">
                <i class="fas fa-code"></i>
            </div>
            <h1 class="text-xl font-bold text-reddit-text dark:text-reddit-darkText">Guys<span class="text-reddit-orange">Into</span>Code</h1>
        </div>

        <!-- Sign up Card -->
        <div class="bg-reddit-card dark:bg-reddit-darkCard rounded-md border border-reddit-border dark:border-reddit-darkBorder p-6 transition-colors">
            <h2 class="text-lg font-bold text-reddit-text dark:text-reddit-darkText mb-1">Sign Up</h2>
            <p class="text-xs text-reddit-muted mb-5">Create your account and start coding together.</p>

            <form method="post" action="./Regist" class="space-y-4">
                <div>
                    <input type="text" name="name" required placeholder="Choose a username" class="w-full pl-4 pr-4 py-3 rounded-full border border-reddit-border dark:border-reddit-darkBorder bg-reddit-bg dark:bg-reddit-darkBg text-reddit-text dark:text-reddit-darkText text-sm focus:ring-1 focus:ring-reddit-blue focus:border-reddit-blue outline-none transition-colors placeholder:text-reddit-muted">
                </div>
                <div>
                    <input type="password" name="pass" required placeholder="Password" class="w-full pl-4 pr-4 py-3 rounded-full border border-reddit-border dark:border-reddit-darkBorder bg-reddit-bg dark:bg-reddit-darkBg text-reddit-text dark:text-reddit-darkText text-sm focus:ring-1 focus:ring-reddit-blue focus:border-reddit-blue outline-none transition-colors placeholder:text-reddit-muted">
                </div>
                <div>
                    <input type="password" name="confirm_pass" required placeholder="Confirm password" class="w-full pl-4 pr-4 py-3 rounded-full border border-reddit-border dark:border-reddit-darkBorder bg-reddit-bg dark:bg-reddit-darkBg text-reddit-text dark:text-reddit-darkText text-sm focus:ring-1 focus:ring-reddit-blue focus:border-reddit-blue outline-none transition-colors placeholder:text-reddit-muted">
                </div>
                <c:if test="${not empty message}">
                <script>Swal.fire({icon:'error',title:'Error!',text:'${message}',confirmButtonColor:'#FF4500'});</script>
                </c:if>
                <c:if test="${not empty errortext}">
                <script>Swal.fire({icon:'warning',title:'Oops!',text:'${errortext}',confirmButtonColor:'#FF4500'});</script>
                </c:if>
                <button type="submit" class="w-full py-3 rounded-full bg-reddit-orange text-white text-sm font-bold hover:bg-reddit-orangeHover transition-colors">
                    Sign Up
                </button>
            </form>

            <div class="mt-5 pt-4 border-t border-reddit-border dark:border-reddit-darkBorder text-center">
                <p class="text-xs text-reddit-secondary dark:text-reddit-darkText">
                    Already a member? <a href="./LoginPage" class="text-reddit-blue font-bold hover:underline">Log In</a>
                </p>
            </div>
        </div>
    </div>
</body>
</html>
