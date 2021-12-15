
function commonInit() {
	// Add application name to all <title>
	var title = $('title').text();
	$('title').text(title + ' - SHC CAMS');

    // Setup tooltips for dropdown toggle buttons
    $(function () {
        $('[data-toggle="dropdown"]').tooltip();
    });
}

function nullify(value) {
	if (typeof value === 'string' && (value.length === 0 || /\s/g.test(value))) {
		return null;
	}

	return value;
}

commonInit();
