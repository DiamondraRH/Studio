<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
        <form action="${pageContext.request.contextPath}/plannifier" method="POST">
            <label class="block text-sm mt-2">
                <span class="text-gray-700 dark:text-gray-400">From</span>
                <input
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    type="date"
                    name="dateDebut"
                />
            </label>
            <label class="block text-sm my-6">
                <span class="text-gray-700 dark:text-gray-400">To</span>
                <input
                    class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                    type="date"
                    name="dateFin"
                />
            </label>

            <div class="w-full overflow-hidden rounded-lg shadow-xs">
                <div class="w-full overflow-x-auto">
                    <table class="w-full whitespace-no-wrap">
                        <thead>
                            <tr
                                class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
                            >
                                <th class="px-4 py-3">Scenes</th>
                                <th class="px-4 py-3">Plateau</th>
                                <th class="px-4 py-3">Estimation</th>
                                <th class="px-4 py-3">Status</th>
                            </tr>
                        </thead>

                        <tbody
                            class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800"
                        >
                            <c:forEach items="${sceneList}" var="scene">
                            <tr class="text-gray-700 dark:text-gray-400">
                                <td class="px-4 py-3">
                                    <div>
                                        <label class="flex items-center dark:text-gray-400">
                                            <input
                                                type="checkbox"
                                                name="idScene"
                                                value="${scene.idScene}"
                                                class="text-purple-600 form-checkbox focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                            />
                                            <span class="ml-2 text-sm font-semibold">
                                                ${scene.titre}
                                            </span>
                                        </label>
                                    </div>
                                </td>
                                <td class="px-4 py-3 text-sm">
                                    ${scene.plateau.lieu} (N°${scene.plateau.numero})
                                </td>
                                <td class="px-4 py-3 text-sm">
                                    ${scene.estimationTournage}
                                </td>
                                <td class="px-4 py-3 text-xs">
                                    <c:choose>
                                        <c:when test="${scene.debutTournage != null && scene.finTournage != null}">
                                            <span
                                                class="px-2 py-1 font-semibold leading-tight text-green-700 bg-green-100 rounded-full dark:bg-green-700 dark:text-green-100"
                                            >
                                                Planned
                                            </span>
                                        </c:when>

                                        <c:when test="${scene.debutTournage == null && scene.finTournage == null}">
                                            <span
                                                class="px-2 py-1 font-semibold leading-tight text-orange-700 bg-orange-100 rounded-full dark:text-white dark:bg-orange-600"
                                            >
                                                Pending
                                            </span>
                                        </c:when>

                                        <c:when test="${scene.finTournage <= now}">
                                            <span
                                                class="px-2 py-1 font-semibold leading-tight text-gray-700 bg-gray-100 rounded-full dark:text-gray-100 dark:bg-gray-700"
                                            >
                                                Filmed
                                            </span>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="py-4">
                <button
                    type="submit"
                    class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                >
                    Schedule
                </button>
            </div>
        </form>
    </div>
        </div></main>
</z:layout>