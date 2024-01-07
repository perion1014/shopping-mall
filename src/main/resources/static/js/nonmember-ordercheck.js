alert('nonmember-ordercheck.js 적용 확인');

function checkNonMemberOrderDetailFromDB(){

    alert('온클릭 함수 진입');

    let nonMemberOrderNo = document.getElementById('nonMemberOrderNo').value;
    let nonMemberOrderName = document.getElementById('nonMemberOrderName').value;

    alert(nonMemberOrderNo);
    alert(nonMemberOrderName);

    fetch('/orders/non-members', {
        method: 'post',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nonMemberOrderNo: nonMemberOrderNo,
            nonMemberOrderName: nonMemberOrderName,
        }),
    }).then(response =>response.json())
        .then(data => {
            alert(data.response);
        })
}