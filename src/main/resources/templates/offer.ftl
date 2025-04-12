<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Oferta</title>
    <link rel="icon" type="image/x-icon" href="/imgs/icons/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <div class="main-container">
        <h1>Oferta</h1>
        <form action="/user/repasar-oferta" method="post">
            <div class="input-element-vertical">
                <label for="nombre">Nombre del artículo (*)</label>
                <input type="text" class="input-lg" id="nombre" placeholder="nombre ..." name="nombre" minlength="6" maxlength="20" required="required" value="${item.nombre}"/>
            </div>
            <div class="input-element-vertical">
                <label for="desc">Descripción</label> 
                <textarea class="input-lg" id="desc" placeholder="características, estado, consejo, ..." name="desc" rows="2" maxlength="100">${item.desc}</textarea>
            </div>
            <div class="input-element-horizontal">
                <label for="precioInicio">Precio mínimo <span class="no-min-display">de salida</span> salida (*)</label>
                <input type="number" class="input-sm" id="precioInicio" name="precioInicio" min="0" max="9999" required="required" value="${item.precioInicio}"/>
            </div>
            <div class="input-element-vertical">
                <label for="url">Enlace a la fotografía</label> 
                <input type="url" class="input-lg" id="urlImagen" placeholder="http://..." name="urlImagen" pattern="https://.*" value="${item.urlImagen}"/>
            </div>
            <div class="input-group">
                <input type="text" class="input-md" id="username" placeholder="nombre" name="username" value="${username}" title="nombre de usuario" required="required" readonly="readonly"/>
                <input type="password" class="input-md" id="password" placeholder="*clave*" name="password" title="password" required="required"/>
            </div>
<#if error>
            <p class="alert">Compruebe escribir la clave correctamente</p>
</#if>            
            <button type="submit" class="btn btn-md"><img class="icon-sm" src="/imgs/icons/accept.png"/>Ofrecer</button>
        </form>

        <#include "common-menu-return.ftl">

    </div>
</body>
</html>