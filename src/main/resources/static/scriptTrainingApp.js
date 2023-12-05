//Script to open the navigation bar menu

$(document).ready(function() {
    $('.navbar_opener').on('click', function() {
        $('.navbar').toggleClass('navbar-opened');
    });
});