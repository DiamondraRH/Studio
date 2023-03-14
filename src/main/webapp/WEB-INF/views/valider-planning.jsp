<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<z:layout>
    <main class="h-full pb-16 overflow-y-auto">
        <div class="container px-6 mx-auto grid">
            <h2
                    class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200"
            >
                Scenes Planning
            </h2>

            <!-- General elements -->
            <div
                    class="px-4 py-3 mb-8 bg-white rounded-lg shadow-md dark:bg-gray-800"
            >
                <div class="flex flex-col overflow-y-auto md:flex-row">
                    <div class="h-32 md:h-auto md:w-1/2">
                    <form action="${pageContext.request.contextPath}/validerPlanning/" method="post">
                        <div class="w-full overflow-hidden rounded-lg shadow-xs">
                            <div class="w-full overflow-x-auto">
                                <table class="w-full whitespace-no-wrap">
                                    <thead>
                                    <tr
                                            class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
                                    >
                                        <th class="px-4 py-3">Scenes</th>
                                        <th class="px-4 py-3">Plateau</th>
                                        <th class="px-4 py-3">Debut tournage</th>
                                        <th class="px-4 py-3">Fin tournage</th>
                                    </tr>
                                    </thead>

                                    <tbody
                                            class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800"
                                    >
                                    <tr class="text-gray-700 dark:text-gray-400">
                                        <td class="px-4 py-3">
                                            <div>
                                                <label class="flex items-center dark:text-gray-400">
                                                    <input
                                                            type="checkbox" checked
                                                            class="text-purple-600 form-checkbox focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                                    />
                                                    <span class="ml-2 text-sm font-semibold">
                                                    Titre scene
                                                </span>
                                                </label>
                                            </div>
                                        </td>
                                        <td class="px-4 py-3 text-sm">
                                            Plateau
                                        </td>
                                        <td class="px-4 py-3 text-sm">
                                            29-03-2023 13:45:00
                                        </td>
                                        <td class="px-4 py-3 text-sm">
                                            29-03-2023 14:45:00
                                        </td>
                                    </tr>
                                    <tr class="text-gray-700 dark:text-gray-400">
                                        <td class="px-4 py-3">
                                            <div>
                                                <label class="flex items-center dark:text-gray-400">
                                                    <input
                                                            type="checkbox" checked
                                                            class="text-purple-600 form-checkbox focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                                    />
                                                    <span class="ml-2 text-sm font-semibold">
                                                    Titre scene
                                                </span>
                                                </label>
                                            </div>
                                        </td>
                                        <td class="px-4 py-3 text-sm">
                                            Plateau
                                        </td>
                                        <td class="px-4 py-3 text-sm">
                                            29-03-2023 13:45:00
                                        </td>
                                        <td class="px-4 py-3 text-sm">
                                            29-03-2023 14:45:00
                                        </td>
                                    </tr>
                                    <tr class="text-gray-700 dark:text-gray-400">
                                        <td class="px-4 py-3">
                                            <div>
                                                <label class="flex items-center dark:text-gray-400">
                                                    <input
                                                            type="checkbox" checked name="scenes" value="idscene;debutTournage;finTournage"
                                                            class="text-purple-600 form-checkbox focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                                    />
                                                    <span class="ml-2 text-sm font-semibold">
                                                    Titre scene
                                                </span>
                                                </label>
                                            </div>
                                        </td>
                                        <td class="px-4 py-3 text-sm">
                                            Plateau
                                        </td>
                                        <td class="px-4 py-3 text-sm">
                                            29-03-2023 13:45:00
                                        </td>
                                        <td class="px-4 py-3 text-sm">
                                            29-03-2023 14:45:00
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="py-4">
                            <button
                                    class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                            >
                                Valider
                            </button>
                        </div>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
</z:layout>