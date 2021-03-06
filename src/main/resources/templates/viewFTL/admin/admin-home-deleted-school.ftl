<#import "../header.ftl" as h>
<@h.header>

</@h.header>
<link rel="stylesheet" type="text/css" href="/scripts/admin-home-popup.css">
<br>
<div id="header">
    <h2>
        <span><@spring.message "hello"/> ,${currentUser.login}</span>

    </h2>
</div>
<div class="modal-body">
    <table class="table table-striped">
        <tr>
            <th colspan="7"><@spring.message "school.list"/></th>
        </tr>
        <tr>
            <th><@spring.message "school.name"/></th>
            <th><@spring.message "school.status"/></th>
        </tr>
    <#list disabledSchools as school>
        <tr>


            <td>${school.name}</td>
            <td>

                <button class="badge badge-danger hand"
                        onclick="showEnableModal(${school.id})"><@spring.message "disabled"/>
                </button>

            </td>
            <td>
                <div class="btn-group flex-btn-group-container">
                    <button id="view" type="button" class="btn btn-info btn-sm"
                            onclick="window.location.href='/freemarker/admin-home/details/${school.id}'">
                        <span class="fa fa-eye"></span>
                        <span class="hidden-md-down"><@spring.message "school.view"/></span>
                    </button>
                    
                </div>
            </td>
            <div id="detail${school.id}" class="detailModal">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2>${school.name}</h2>
                    </div>
                    <div class="modal-body">
                        <dl class="row-md jh-entity-details">
                            <dt><span><@spring.message "school.name"/></span></dt>
                            <dd>
                                <div>
                                    <span>${school.name}</span>
                                </div>
                            </dd>
                        </dl>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-info"
                                onclick=removeDetailModal(${school.id})>
                            <span class="fa fa-arrow-left"></span>&nbsp;<span>Back</span>
                        </button>
                    </div>
                </div>
            </div>

            <div class="deleteModal" id="delete${school.id}">
                <div class="modal-content2">
                    <div class="modal-body">
                        <h2><@spring.message "school.enableconf"/> ${school.name} <@spring.message "school.enable?"/></h2>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                onclick="removeEnableModal(${school.id})">
                            <span class="fa fa-ban"></span>&nbsp;<span><@spring.message "school.cancel"/></span>
                        </button>
                        <button type="submit" class="btn btn-success"
                                onclick="window.location.href='/freemarker/admin-home/school-toggle/${school.id}'">
                            <span class="fa fa-check"></span><span><@spring.message "yes"/></span>
                        </button>
                    </div>
                </div>
            </div>

        </tr>
    </#list>
    <#if longs gt 0 >
        <div>
            <form id="use">
                <select name="size" class="custom-select" id="mySelect" onchange="onChange()">
                    <#list [5, 10, 15, 20] as s>
                        <#if sizes == s>
                            <option value="${s}" selected="selected">${s} items</option>
                        <#else>
                            <option value="${s}">${s} items</option>
                        </#if>
                    </#list>
                </select>
            </form>
            <nav aria-label="...">
                <ul class="pagination">
                    <#if current gt 0 >
                        <li class="page-item">
                            <a class="page-link" href="?page=${current-1}&size=${sizes}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                    </#if>

                    <#list 0..longs-1 as i>
                        <#if current != i>
                            <li class="page-item"><a class="page-link" href="?page=${i}&size=${sizes}">${i+1}</a></li>
                        <#else>
                            <li class="page-item active"><span class="page-link">${i+1}</span></li>
                        </#if>
                    </#list>

                    <#if current < longs-1  >
                        <li class="page-item">
                            <a class="page-link" href="?page=${current+1}&size=${sizes}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </#if>
                </ul>
            </nav>
        </div>
    </#if>

    </table>
    <span></span>

</div>
<div class="modal-footer">
    <button type="button" class="btn btn-info"
            onclick="window.location.href='/freemarker/admin-home/'">
        <span class="fa fa-arrow-left"></span>&nbsp;<span><@spring.message "back"/></span>
    </button>
</div>
<script src="/scripts/admin-home.js"></script>
<@h.footer>

</@h.footer>
