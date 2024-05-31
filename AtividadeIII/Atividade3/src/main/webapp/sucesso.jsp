<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Curriculo JSP</title>

    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<div class="isolate bg-white p-36">

    <div class="mx-auto max-w-2xl text-center">
        <h1 class="text-2xl font-bold tracking-tight text-gray-900">Cadastro de Currículo</h1>
        <p class="mt-2 leading-8 text-gray-600">Aplicação para vaga de desenvolvedor concluída</p>
    </div>

    <div>
        <div class="mt-6 border-t border-gray-100">
            <dl class="divide-y divide-gray-100">

                <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                    <dt class="text-sm font-medium leading-6 text-gray-900">Nome Informado</dt>
                    <dd class="mt-1 text-sm leading-6 font-light sm:col-span-2 sm:mt-0">${requestScope.curriculo.nome}</dd>
                </div>

                <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                    <dt class="text-sm font-medium leading-6 text-gray-900">Data de Nascimento Informada</dt>
                    <dd class="mt-1 text-sm leading-6 font-light sm:col-span-2 sm:mt-0">${requestScope.curriculo.dtNascimento}</dd>
                </div>

                <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                    <dt class="text-sm font-medium leading-6 text-gray-900">Idioma Nativo selecionado</dt>
                    <dd class="mt-1 text-sm leading-6 font-light sm:col-span-2 sm:mt-0">${requestScope.curriculo.idioma}</dd>
                </div>

                <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                <dt class="text-sm font-medium leading-6 text-gray-900">Habilidades Técnicas selecionadas</dt>
                    <dd class="mt-1 text-sm leading-6 font-light sm:col-span-2 sm:mt-0">${requestScope.curriculo.habilidades}</dd>
                </div>


                <div class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                    <dt class="text-sm font-medium leading-6 text-gray-900">Informações adicionais incluídas</dt>
                    <dd class="mt-1 text-sm leading-6 font-light sm:col-span-2 sm:mt-0">${requestScope.curriculo.texto}</dd>
                </div>


            </dl>
        </div>
    </div>

</div>
</body>
</html>
