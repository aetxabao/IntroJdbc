<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Repaso</title>
    <link rel="icon" type="image/x-icon" href="/imgs/icons/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/layout.css">
</head>
<body>
    <div class="main-container">
        <h1>Oferta</h1>
        <form class="layout" action="/user/confirmar-oferta/${item.id}" method="post">
            <div class="w12 input-element-vertical">
                <input type="text" class="input-lg" id="nombre" value='${item.nombre}' name="nombre" minlength="6" maxlength="20" required="required"/>
            </div>
            <div class="w12 input-element-vertical">
                <textarea class="input-lg" id="desc" name="desc" rows="3" maxlength="100">${item.desc}</textarea>
            </div>
            <div class="w6 img">
                <img src="${item.urlImagen}" class="icon-xl" alt="imagen item"/>
                <#--  <img src="/imgs/cosa.png" class="icon-xl" alt="mazo subasta"/>  -->
                <input type="hidden" name="urlImagen" value="${item.urlImagen}"/>
            </div>
            <div class="w6 input-element-vertical">
                <div class="input-element-left">
                    <input type="number" class="input-sm" id="precioInicio" name="precioInicio" min="1" max="9999" value="${item.precioInicio}" required="required"/>
                    <label class="lbl-lg" for="price">€</label> 
                </div>
                <div class="input-element-left">
                    <input type="text" class="input-md" id="username" placeholder="nombre" name="username" value="${username}" title="nombre de usuario" required="required" readonly="readonly"/>
                </div>
                <div class="input-element-left">
                    <input type="password" class="input-md" id="password" placeholder="*clave*" name="password" title="password" required="required"/>
                </div>
            </div>
<#if error>            
            <p class="w12 alert">Compruebe escribir la clave correctamente</p>
</#if>
            <div class="w12 input-element-center">
                <button type="submit" class="btn btn-lg"><img class="icon-sm" src="/imgs/icons/accept.png"/>Confirmar</button>
            </div>
        </form>

        <#include "common-menu-return.ftl">

</body>
</html>