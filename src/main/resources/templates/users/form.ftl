<#import "../layout.ftl" as layout>

<#if (mode!'create') == "create">
    <#assign formAction = "/users" />
    <#assign submitLabel = "Создать" />
    <#assign pageTitle = "Новый пользователь" />
<#else>
    <#assign formAction = "/users/${form.id()}" />
    <#assign submitLabel = "Сохранить" />
    <#assign pageTitle = "Редактирование пользователя" />
</#if>

<@layout.page title=pageTitle>
    <div class="page-header">
        <h2>${pageTitle}</h2>
    </div>

    <div class="card form-card">
        <div class="card-body">
            <form method="post" action="${formAction}">
                <div class="field<#if bindingResult?? && bindingResult.hasFieldErrors('name')> has-error</#if>">
                    <label for="name">Имя</label>
                    <input
                            type="text"
                            id="name"
                            name="name"
                            required
                            minlength="2"
                            maxlength="50"
                            placeholder="Иван Иванов"
                            value="${form.name()?html}"
                    />

                    <#if bindingResult?? && bindingResult.hasFieldErrors("name")>
                        <span class="field-error">${bindingResult.getFieldError("name").defaultMessage?html}</span>
                    </#if>
                </div>

                <div class="field<#if bindingResult?? && bindingResult.hasFieldErrors('email')> has-error</#if>">
                    <label for="email">Email</label>
                    <input
                            type="email"
                            id="email"
                            name="email"
                            required
                            maxlength="50"
                            placeholder="ivan@example.com"
                            value="${form.email()?html}"
                    />

                    <#if bindingResult?? && bindingResult.hasFieldErrors("email")>
                        <span class="field-error">${bindingResult.getFieldError("email").defaultMessage?html}</span>
                    </#if>
                </div>

                <div class="form-actions">
                    <button class="btn" type="submit">${submitLabel}</button>
                    <a class="btn btn-outline" href="/users">Отмена</a>
                </div>
            </form>
        </div>
    </div>
</@layout.page>
