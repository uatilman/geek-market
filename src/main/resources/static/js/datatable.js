// https://datatables.net/

$(document).ready(function () {
    var table = $('.table').DataTable({
        "ajax": {
            "url": "/geekmarket/api/products",
            "dataSrc": "" //,
//	      "type": "POST"
        },
//	      "processing": true,
//        "serverSide": true,
        "columns": [
            {"data": "id"},
            {"data": "title"},
            {"data": "price"},
            {
                "data": "id",
                "render": function (data, type, full, meta) {
                    return '<button type="button" class="btn btn-primary add-item-btn" link="' + data + '">Купить</button>';
                }
            }
        ],
        "initComplete": function (settings, json) {
            $('.add-item-btn').on('click', function (event) {
                var btnLink = $(this).attr('link');
                // console.log(btnLink);
                $.ajax({
                    url: "/geekmarket/api/cart_items",
                    type: "POST",
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(header, token);
                    },
                    data: {
                        "id": btnLink
                    }
                }).done(function () {
                    alert("Товар добавлен в корзину");
                });
            });
        }
    })
});