(function() {
  var key = 'guys-into-code-theme';
  function getTheme() { return localStorage.getItem(key) || 'light'; }
  function setTheme(v) { localStorage.setItem(key, v); }
  function applyTheme(v) {
    if (v === 'dark') document.documentElement.classList.add('dark');
    else document.documentElement.classList.remove('dark');
  }
  function toggleTheme() {
    var next = getTheme() === 'dark' ? 'light' : 'dark';
    setTheme(next);
    applyTheme(next);
    return next;
  }
  applyTheme(getTheme());
  window.themeToggle = toggleTheme;
})();
