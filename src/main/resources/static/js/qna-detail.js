function openQnaWindow(itemNo, qnaNo) {
    let url = '/items/' + itemNo + '/qna/' + qnaNo;
    window.open(url, 'name', 'resizable=no,width=600,height=500');
    return false;
}