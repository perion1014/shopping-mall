document.addEventListener("DOMContentLoaded", e => {
    const successMessageElement = document.getElementById('successMessage');
    const successMessage = successMessageElement.getAttribute('data-success-message');

    if (successMessage) {
        Swal.fire({
            icon: 'success',
            title: `<span style=font-size:1rem> ${successMessage} </span>`,
            iconColor:'#ffb94f',
            width:'25rem',
            height:'12rem',
        });
    }
});