<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="icon" type="image/x-icon" href="/imgs/icons/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
    <div class="main-container">
        <h1>Subasta a ciegas</h1>
        <div class="img-container no-min-display">
            <div class="img">
                <img src="/imgs/silencio.svg" class="icon-lg" alt="silencio"/>
            </div>
            <div class="img">
                <img src="/imgs/mazo.svg" class="icon-lg" alt="mazo subasta"/>
            </div>
        </div>
        <form action="/autenticar" method="post">
            <p>Por favor, identifíquese para acceder</p>
            <div class="input-group">
                <input type="text" class="input-md" id="username" placeholder="nombre" name="username" value="${username}"/>
                <input type="password" class="input-md" id="password" placeholder="*clave*" name="password"/>
            </div>
<#if error>            
            <p class="alert">Compruebe sus credenciales</p>
</#if>
            <button type="submit" class="btn btn-md"><img class="icon-sm" src="/imgs/icons/entrar.png"/>Entrar</button>
        </form>
    </div>
</body>
</html>