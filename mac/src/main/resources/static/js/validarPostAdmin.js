(function () {
                        'use strict';
            window.addEventListener('load', function() {

            var forms = document.getElementsByClassName('needs-validation');

            var validation = Array.prototype.filter.call(forms, function(form) {

            form.addEventListener('submit', function(event) {
             if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                                document.getElementById("esconderVaga").value = 'false';
                                console.log('Entro th');
             }if (form.checkValidity() === true){
                                document.getElementById("esconderVaga").value = 'true';
             }
              form.classList.add('was-validated');
              }, false);
              });
              },false);
              })();

            $( document ).ready(function() {


                $("#formVaga").submit(function(event) {
                        event.preventDefault();
                        var resultado = document.getElementById("esconderVaga").value;
                        if(resultado == 'true'){
                            ajaxPost();
                        }
                });

                $("#formCriarEntrevista").submit(function(event) {
                       event.preventDefault();
                       var resultado = document.getElementById("esconderVaga").value;
                       if(resultado == 'true'){
                            criarEntrevistaPost();
                       }
                });

                $("#formNovaEntrevista").submit(function(event) {
                        event.preventDefault();
                        var resultado = document.getElementById("esconderVaga").value;
                        if(resultado == 'true'){
                              novaEntrevistaPost();
                        }
                });

                $("#formEmpresa").submit(function(event) {
                        event.preventDefault();
                        var resultado = document.getElementById("esconderVaga").value;
                        if(resultado == 'true'){
                               novaEmpresaPost();
                        }
                });

                $("#formAtualizarEmpresa").submit(function(event) {
                        event.preventDefault();
                        var resultado = document.getElementById("esconderVaga").value;
                        if(resultado == 'true'){
                               atualizarEmpresaPost();
                        }
                });


                $("#formAtualizarVaga").submit(function(event) {
                         event.preventDefault();
                         var resultado = document.getElementById("esconderVaga").value;
                         if(resultado == 'true'){
                                atualizarVagaPost();
                         }
                });

                function ajaxPost(){

                  // PREPARE FORM DATA
                  var descricaoHabilidades = [];
                  descricaoHabilidades[0] = $("#habilidade1").val();
                  descricaoHabilidades[1] = $("#habilidade2").val();
                  descricaoHabilidades[2] = $("#habilidade3").val();
                  descricaoHabilidades[3] = $("#habilidade4").val();
                  descricaoHabilidades[4] = $("#habilidade5").val();

                  var beneficios = [];
                  beneficios[0] = $("#beneficio1").val();
                  beneficios[1] = $("#beneficio2").val();
                  beneficios[2] = $("#beneficio3").val();
                  beneficios[3] = $("#beneficio4").val();

                  var formData = {
                    titulo : $("#titulo").val(),
                    cidade :  $("#cidade").val(),
                    quantidade : $("#quantidade").val(),
                    categoria : $("#categoria").val(),
                    formacao : $("#formacao").val(),
                    descricaoHabilidades: descricaoHabilidades,
                    beneficios : beneficios,
                    responsabilidade: $("#responsabilidades").val(),
                    salario: $("#validationSalario").val(),
                    observacao: $("#observacao").val(),
                    turno: $("#turno").val(),
                    dataLimite: $("#dataLimite").val(),
                  }

                  // DO POST
                  $.ajax({
                  type : "POST",
                  contentType : "application/json",
                  url : "/vaga/criarVaga",
                  data : JSON.stringify(formData),
                  dataType : 'json',
                  success : function(result) {
                        alert("Salvo com sucesso!");
                  },
                  error : function(e) {
                        alert("ERRO!! Causa: "+ e.responseText);
                  }
                  });
                }

                function criarEntrevistaPost(){

                    var listDestinatarios = [];
                    listDestinatarios[0] = $("#destinatarios").val();
                    var dadosEmail = {
                            remetente : $("#remetente").val(),
                            destinatarios: listDestinatarios,
                            assunto :$("#assunto").val(),
                            corpo: $("#summernote").val(),
                    }

                    // PREPARE FORM DATA
                    var formEntrevistaCriar = {
                    nomeCliente : $("#nomeCliente").val(),
                    idCliente : $("#idCliente").val(),
                    data : $("#data").val(),
                    horario : $("#horario").val(),
                    local : $("#local").val(),
                    mensagemEmail : dadosEmail,
                    }

                    // DO POST
                    $.ajax({
                    type : "POST",
                    contentType : "application/json",
                    url : "/entrevista/criar",
                    data : JSON.stringify(formEntrevistaCriar),
                    dataType : 'json',
                    success : function(result) {
                          alert("Entrevista agendada com sucesso!");
                    },
                    error : function(e) {
                          alert("ERRO!! Causa: "+ e.responseText);
                    }
                    });
                    }

                function novaEntrevistaPost(){

                     var listDestinatarios = [];
                     listDestinatarios[0] = $("#destinatarios").val();
                     var dadosEmail = {
                     remetente : $("#remetente").val(),
                     destinatarios: listDestinatarios,
                     assunto :$("#assunto").val(),
                     corpo: $("#summernote").val(),
                     }

                     // PREPARE FORM DATA
                     var formEntrevistaNova = {
                     nomeCliente : $("#nomeCliente").val(),
                     idCliente : $("#idCliente").val(),
                     data : $("#data").val(),
                     horario : $("#horario").val(),
                     local : $("#local").val(),
                     mensagemEmail : dadosEmail,
                     }

                     // DO POST
                     $.ajax({
                     type : "POST",
                     contentType : "application/json",
                     url : "/entrevista/novaVaga",
                     data : JSON.stringify(formEntrevistaNova),
                     dataType : 'json',
                     success : function(result) {
                           alert("Entrevista agendada com sucesso!");
                     },
                     error : function(e) {
                           alert("ERRO!! Causa: "+ e.responseText);
                     }
                     });
                     }

                function novaEmpresaPost(){

                      // PREPARE FORM DATA
                      var formNovaEmpresa = {
                      cnpj : $("#cnpj").val(),
                      razaoSocial : $("#razaoSocial").val(),
                      representante : $("#representante").val(),
                      telefone : $("#telefone").val(),
                      rua : $("#rua").val(),
                      bairro : $("#bairro").val(),
                      }

                      // DO POST
                      $.ajax({
                      type : "POST",
                      contentType : "application/json",
                      url : "/empresa/nova",
                      data : JSON.stringify(formNovaEmpresa),
                      dataType : 'json',
                      success : function(result) {
                           alert("Empresa criada com sucesso!");
                      },
                      error : function(e) {
                           alert("ERRO! Causa: "+ e.responseText);
                      }
                      });
                      }

                function atualizarEmpresaPost(){

                       // PREPARE FORM DATA
                       var formNovaEmpresa = {
                       cnpj : $("#cnpj").val(),
                       razaoSocial : $("#razaoSocial").val(),
                       representante : $("#representante").val(),
                       telefone : $("#telefone").val(),
                       rua : $("#rua").val(),
                       bairro : $("#bairro").val(),
                       }

                       // DO POST
                       $.ajax({
                       type : "POST",
                       contentType : "application/json",
                       url : "/empresa/atualizar",
                       data : JSON.stringify(formNovaEmpresa),
                       dataType : 'json',
                       success : function(result) {
                            alert("Empresa atualizada com sucesso!");
                       },
                       error : function(e) {
                            alert("ERRO! Causa: "+ e.responseText);
                       }
                       });
                       }

                function atualizarVagaPost(){

                        // PREPARE FORM DATA
                        var descricaoHabilidades = [];
                        descricaoHabilidades[0] = $("#habilidade1").val();
                        descricaoHabilidades[1] = $("#habilidade2").val();
                        descricaoHabilidades[2] = $("#habilidade3").val();
                        descricaoHabilidades[3] = $("#habilidade4").val();
                        descricaoHabilidades[4] = $("#habilidade5").val();

                        var beneficios = [];
                        beneficios[0] = $("#beneficio1").val();
                        beneficios[1] = $("#beneficio2").val();
                        beneficios[2] = $("#beneficio3").val();
                        beneficios[3] = $("#beneficio4").val();

                        var atualizarVagaForm = {
                          titulo : $("#titulo").val(),
                          cidade :  $("#cidade").val(),
                          quantidade : $("#quantidade").val(),
                          categoria : $("#categoria").val(),
                          formacao : $("#formacao").val(),
                          descricaoHabilidades: descricaoHabilidades,
                          beneficios : beneficios,
                          responsabilidade: $("#responsabilidades").val(),
                          salario: $("#validationSalario").val(),
                          observacao: $("#observacao").val(),
                          turno: $("#turno").val(),
                          dataLimite: $("#dataLimite").val(),
                          numeroInscritos: $("#numeroInscritos").val(),
                          id: $("#idVaga").val(),
                        }

                        // DO POST
                        $.ajax({
                        type : "POST",
                        contentType : "application/json",
                        url : "/vaga/atualizar",
                        data : JSON.stringify(atualizarVagaForm),
                        dataType : 'json',
                        success : function(result) {
                             alert("Vaga atualizada com sucesso!");
                        },
                        error : function(e) {
                             alert("ERRO! Causa: "+ e.responseText);
                        }
                        });
                        }
            })