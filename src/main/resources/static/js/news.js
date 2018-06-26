$(document).ready(function () {

    axios.get("/api/new/getall").then(function (res) {
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#preview-news-img').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }

        if (res.data.success) {
            for(var news of res.data.data){
                $('#Mytable > tbody').append(`
                <tr>
                            <td>${news.newId}</td>
                            <td>${news.title}</td>
                            <td>${news.content}</td>
                            <td><img th:src="${news.img}" alt="không hiểu"></td>
                            <td>${news.createdDate}</td>
                            <td><button class="btn btn-default btn-edit-news" data-id="${news.newId}">Sửa</button> <button class="btn btn-danger btn-remove-news" data-id="${news.newId}">Xóa</button></td>
                        </tr>
                `)
            }
            $('#Mytable').DataTable();
            // console.log($(".btn-remove-news"))
        }
        $(".btn-remove-news").on('click',function (res) {
            var newsInfo = $(this).data("id");
            swal({
                    title: 'Are you sure?',
                    text: 'You wont be able to revert this',
                    type: 'warning',
                    showCancelButton: true
                }
            ).then(function (result) {
                if (result.value){
                    axios.post("/api/new/delete-new/",{newId: newsInfo}).then(function (res) {
                        if(res.data.success) {
                            swal(
                                'Well done',
                                res.data.message,
                                'success'
                            ).then(function () {
                                location.reload();
                            });
                        }else {
                            swal(
                                'Error',
                                res.data.message,
                                'error'
                            );
                        }
                    },function (err) {
                        swal(
                            'Error',
                            'Errors occured while deleting news',
                            'error'
                        )
                    })

                }
            })
        })
        $(".btn-edit-news").on('click',function () {
            var newsInfo = $(this).data("id");





            $(".box-primary").show()

        })

    })


})