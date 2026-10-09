<#import "../layout.ftl" as layout>

<@layout.page title="Пользователи">
    <div class="page-header">
        <h2>Пользователи</h2>
        <a class="btn" href="/users/new">+ Добавить пользователя</a>
    </div>

    <#if users?has_content>
        <div class="table-wrap">
            <table class="table">
                <thead>
                <tr>
                    <th class="col-id">ID</th>
                    <th>Имя</th>
                    <th>Email</th>
                    <th class="col-date">Дата создания</th>
                    <th class="col-actions">Действия</th>
                </tr>
                </thead>
                <tbody>
                <#list users as user>
                    <tr>
                        <td class="col-id">${user.id()}</td>
                        <td class="user-name">${user.name()}</td>
                        <td class="user-email">${user.email()}</td>
                        <td class="col-date">${user.createdAt()}</td>
                        <td class="col-actions">
                            <a class="btn btn-outline btn-sm" href="/users/${user.id()}/edit">Изменить</a>

                            <form
                                    method="post"
                                    action="/users/${user.id()}/delete"
                                    style="display: inline"
                                    onsubmit="return confirm('Удалить пользователя ${user.name()?js_string}?')"
                            >
                                <button class="btn btn-danger btn-sm" type="submit">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    <#else>
        <div class="empty-state">
            <p>Пользователи не найдены</p>
            <a class="btn" href="/users/new">+ Добавить пользователя</a>
        </div>
    </#if>
</@layout.page>
