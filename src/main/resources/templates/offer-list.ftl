<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado</title>
    <link rel="icon" type="image/x-icon" href="/imgs/icons/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/layout.css">
</head>
<body>
    <div class="main-container">
        <h1>Subasta</h1>
        <hr/>

    <#if items?size == 0>
        <p class="w12 alert bigger-margin-bottom">No hay ofertas</p>
        <hr/>
    </#if>

    <#list items as item>
        <div class="layout">
            <div class="w4 img">
                <#--  <img src="/imgs/icons/cosa.png" class="icon-xl" alt="foto"/>  -->
                <img src="${item.urlImagen}" class="icon-xl" alt="foto"/>
            </div>
            <div class="w8 input-element-vertical center">
                <h2>${item.nombre}</h2>
                <p>Precio salida: <b>${item.precioInicio} €</b></p>
                <a class="btn btn-md no-margin-bottom" href="/user/pujar/${item.id}"><img class="icon-sm" src="/imgs/icons/buy.png" alt="login"/>Pujar</a>
            </div>
            <p class="w12 desc no-min-display">${item.desc}</p>
            <hr class="w12"/>
        </div>
    </#list>
    

        <div class="input-element-horizontal bigger-margin-bottom">
            <form action="/menu" method="get">
                <button type="submit" class="btn btn-lg no-margin-bottom no-margin-top"><img class="icon-sm" src="/imgs/icons/menu.png"/>Menú</button>
            </form>
        </div>

        <#include "common-menu-return.ftl">

    </div>
</body>
</html>