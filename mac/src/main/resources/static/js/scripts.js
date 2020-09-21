            /*!
                * Start Bootstrap - SB Admin v6.0.0 (https://startbootstrap.com/templates/sb-admin)
                * Copyright 2013-2020 Start Bootstrap
                * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-sb-admin/blob/master/LICENSE)
                */
                (function($) {
                "use strict";

                // Add active state to sidbar nav links
            var path = window.location.href; // because the 'href' property of the DOM element is the absolute path
                    $("#layoutSidenav_nav .sb-sidenav a.nav-link").each(function() {
                        if (this.href === path) {
                            $(this).addClass("active");
                        }
                    });

                // Toggle the side navigation
                $("#sidebarToggle").on("click", function(e) {
                    e.preventDefault();
                    $("body").toggleClass("sb-sidenav-toggled");
                });
            })(jQuery);

            function mascarar(o,f){
                v_obj=o
                v_fun=f
                setTimeout("execmascara()",1)
            }
            function execmascara(){
                v_obj.value=v_fun(v_obj.value);
            }

            function id( el ){
                return document.getElementById( el );
            }

            function removeEspaco(v){
                v=v.replace(/\D/g,"");
                return v;
            }

            function mascaraCpf(v){
                    v=v.replace(/\D/g,""); //Remove tudo o que não é dígito
                    v=v.replace(/(\d{3})(\d)/, '$1.$2'); //Coloca parênteses em volta dos dois primeiros dígitos
                    v=v.replace(/(\d{3})(\d)/, '$1.$2');    //Coloca hífen entre o quarto e o quinto dígitos
                    v=v.replace(/(\d{3})(\d{1,2})/, '$1-$2');
                    v=v.replace(/(-\d{2})\d+?$/, '$1');
                    return v;
            }

            function mascaraTel(v){
                    v=v.replace(/\D/g,""); //Remove tudo o que não é dígito
                    v=v.replace(/^(\d{2})(\d)/g,"($1) $2"); //Coloca parênteses em volta dos dois primeiros dígitos
                    v=v.replace(/(\d)(\d{4})$/,"$1-$2");    //Coloca hífen entre o quarto e o quinto dígitos
                    return v;
                }

            function soNumeros(v){
                return v.replace(/\D/g,"")
                }

            function telefone(v){
                v=v.replace(/\D/g,"") //Remove tudo o que não é dígito
                v=v.replace(/^(\d\d)(\d)/g,"($1) $2") //Coloca parênteses em volta dos dois primeiros dígitos
                v=v.replace(/(\d{4})(\d)/,"$1 - $2") //Coloca hífen entre o quarto e o quinto dígitos
                return v
                }

            function mascaraDoCpf(v){
                v=v.replace(/\D/g,"") //Remove tudo o que não é dígito
                v=v.replace(/(\d{3})(\d)/,"$1.$2") //Coloca um ponto entre o terceiro e o quarto dígitos
                v=v.replace(/(\d{3})(\d)/,"$1.$2") //Coloca um ponto entre o terceiro e o quarto dígitos
                //de novo (para o segundo bloco de números)
                v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2") //Coloca um hífen entre o terceiro e o quarto dígitos
                return v
                }

            function cep(v){
                v=v.replace(/\D/g,"") //Remove tudo o que não é dígito
                v=v.replace(/(\d{2})(\d)/,"$1.$2") //Coloca um ponto entre o terceiro e o quarto dígitos
                v=v.replace(/(\d{3})(\d{1,3})$/,"$1-$2") //Coloca um hífen entre o terceiro e o quarto dígitos
                return v
                }

            function cnpj(v){
                v=v.replace(/\D/g,"") //Remove tudo o que não é dígito
                v=v.replace(/^(\d{2})(\d)/,"$1.$2") //Coloca ponto entre o segundo e o terceiro dígitos
                v=v.replace(/^(\d{2}).(\d{3})(\d)/,"$1.$2.$3") //Coloca ponto entre o quinto e o sexto dígitos
                v=v.replace(/.(\d{3})(\d)/,".$1/$2") //Coloca uma barra entre o oitavo e o nono dígitos
                v=v.replace(/(\d{4})(\d)/,"$1-$2") //Coloca um hífen depois do bloco de quatro dígitos
                return v
                }

            function romanos(v){
                v=v.toUpperCase() //Maiúsculas
                v=v.replace(/[^IVXLCDM]/g,"") //Remove tudo o que não for I, V, X, L, C, D ou M
                //Essa é complicada! Copiei daqui: http://www.diveintopython.org/refactoring/refactoring.html 41
                while(v.replace(/^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$/,"")!="")
                v=v.replace(/.$/,"")
                return v
                }

            function mascaraData(v){
                v=v.replace(/\D/g,"") //Remove tudo o que não é dígito
                v=v.replace(/^(\d{2})(\d)/,"$1/$2") //Coloca ponto entre o segundo e o terceiro dígitos
                v=v.replace(/.(\d{2})(\d)/,"$1/$2") //Coloca uma barra entre o oitavo e o nono dígitos
                v=v.replace(/(\d{2})(\d)/,"$1/$2") //Coloca um hífen depois do bloco de quatro dígitos
                return v
            }

            function moeda(v){
                v=v.replace(/\D/g,"") // permite digitar apenas numero
                v=v.replace(/(\d{1})(\d{15})$/,"$1.$2") // coloca ponto antes dos ultimos digitos
                v=v.replace(/(\d{1})(\d{11})$/,"$1.$2") // coloca ponto antes dos ultimos 13 digitos
                v=v.replace(/(\d{1})(\d{8})$/,"$1.$2") // coloca ponto antes dos ultimos 10 digitos
                v=v.replace(/(\d{1})(\d{5})$/,"$1.$2") // coloca ponto antes dos ultimos 7 digitos
                v=v.replace(/(\d{1})(\d{1,2})$/,"$1,$2") // coloca virgula antes dos ultimos 4 digitos
                return v;
                }
