// Docs scripts
$(function() {
    var $window = $(window)
    var $body   = $(document.body)

    $body.scrollspy({
        target: '.bs-docs-sidebar'
    });
    $window.on('load', function () {
        $body.scrollspy('refresh')
    });

    // Kill links
    $('.bs-docs-container [href="#"]').click(function (e) {
        e.preventDefault()
    });

    // Sidenav affixing
    setTimeout(function () {
        var $sideBar = $('.bs-docs-sidebar')

        $sideBar.affix({
            offset: {
                top: function () {
                    var offsetTop      = $sideBar.offset().top
                    var sideBarMargin  = parseInt($sideBar.children(0).css('margin-top'), 10)
                    var navOuterHeight = $('.bs-docs-nav').height()

                    return (this.top = offsetTop - navOuterHeight - sideBarMargin)
                },
                bottom: function () {
                    return (this.bottom = $('.bs-docs-footer').outerHeight(true))
                }
            }
        })
    }, 100);

    setTimeout(function () {
        $('.bs-top').affix()
    }, 100);
});
