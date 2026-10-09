<#macro page title="Пользователи">
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>${title} — User CRUD</title>
        <link rel="stylesheet" href="/css/style.css"/>
    </head>
    <body>
    <header class="site-header">
        <div class="container">
            <h1 class="brand">
                User CRUD GRID
                <span class="brand-subtitle">Spring MVC + Apache FreeMarker</span>
            </h1>
            <nav class="site-nav">
                <a href="/users">Пользователи</a>
                <a href="/users/new">Добавить пользователя</a>
            </nav>
        </div>
    </header>

    <main class="container">
        <#nested />
    </main>

    <footer class="site-footer">
        <div class="container">Spring MVC demo application</div>
    </footer>
    </body>
    </html>
</#macro>
