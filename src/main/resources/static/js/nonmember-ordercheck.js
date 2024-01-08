//alert('nonmember-ordercheck.js 적용 확인');

function checkNonMemberOrderDetailFromDB(){

    //alert('온클릭 함수 진입');

    let checkNonMemberOrderNo = document.getElementById('nonMemberOrderNo').value;
    let checkNonMemberOrderName = document.getElementById('nonMemberOrderName').value;

    // alert(checkNonMemberOrderNo);
    // alert(checkNonMemberOrderName);

    fetch('/orders/non-members', {
        method: 'post',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            checkNonMemberOrderNo: checkNonMemberOrderNo,
            checkNonMemberOrderName: checkNonMemberOrderName,
        }),
    }).then(response =>response.json())
        .then(data => {
            if(JSON.stringify(data.response) === '"해당 주문번호, 혹은 주문자명으로 등록된 주문이 없습니다."') {
                alert(data.response);
                location.reload();
            }
            else{
                //alert(data.response);
                location.href = "/orders/" + checkNonMemberOrderNo;
            }

        })
}