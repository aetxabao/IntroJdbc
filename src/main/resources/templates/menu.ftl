<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú</title>
    <link rel="icon" type="image/x-icon" href="/imgs/icons/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <div class="main-container">
        <#if isAdministrator>
            <h1>Administración</h1>

        <#else>
            <h1>Menú Usuario</h1>
            <div class="input-element-vertical center">
                <a class="btn btn-md no-margin-bottom" href="/user/crear-oferta"><img class="icon-sm" src="/imgs/icons/offer.png" alt="login"/>Subir oferta</a>
                <br/>
                <a class="btn btn-md no-margin-bottom" href="/user/ver-ofertas"><img class="icon-sm" src="/imgs/icons/buy.png" alt="alert"/>Ver ofertas</a>
                <br/>
                <a class="btn btn-md no-margin-bottom" href="/exit"><img class="icon-sm" src="/imgs/icons/exit.png" alt="exit"/>Salir</a>
                <br/>
            </div>
        </#if>
    </div>
</body>
</html>