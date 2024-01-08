
function checkNonMemberOrderCancel(nonMemberOrderNo){

    let nonMemberOrderCancelCheck = confirm('현재 조회 중인 주문을 취소하시겠습니까?');

    if(nonMemberOrderCancelCheck === true){

    fetch('/orders/' + nonMemberOrderNo, {
        method: 'post',
        headers:{
            "Content-type": "application/json"
        },
        body:JSON.stringify({
            nonMemberOrderNo: nonMemberOrderNo
        }),
    }).then(response => response.json())
        .then(data => {
            //alert(JSON.stringify(data.response));
            location.href = "/orders/delete-success";
        })
    } else{
        alert('취소하셨습니다.');
    }

}
