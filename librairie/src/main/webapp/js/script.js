

$( document ).ready(function() {

	$('.btnDel').click(function() {
		return confirm('Are you sure you want to delete this ?');
	});

	$(".chosen-select").chosen();

	$('.datepicker').pickadate({
		format : 'yyyy-mm-dd',
		formatSubmit : 'yyyy-mm-dd',
		hiddenName : true,
		selectYears: 500,
		max : 0, 
		editable : true
	});

	window.setTimeout(function() {
	    $(".alert").fadeTo(500, 0).slideUp(500, function(){
	        $(this).remove(); 
	    });
	}, 3500);
	
	
	$('#authorsTable').dataTable({
		"pageLength": 9,
		"scrollY": 435,
		"columnDefs": [
		      { "width": "38%", "targets": 0 },
		      { "width": "20%", "targets": 1 },
		      { "width": "20%", "targets": 2 },
		      { "width": "22%", "targets": 3 }
		    ]
	});
	
	$('#bookTable').dataTable({
		"pageLength": 8,
		"scrollY": 390,
		"columnDefs": [
		      { "width": "14%", "targets": 0 },
		      { "width": "30%", "targets": 1 },
		      { "width": "20%", "targets": 2 },
		      { "width": "10%", "targets": 3 },
		      { "width": "11%", "targets": 4 },
		      { "width": "15%", "targets": 5 }
		    ]
	});
	
	$('#borTable').dataTable({
		"pageLength": 7,
		"scrollY": 330,
		"columnDefs": [
		      { "width": "8%", "targets": 0 },
		      { "width": "32%", "targets": 1 },
		      { "width": "10%", "targets": 2 },
		      { "width": "10%", "targets": 3 },
		      { "width": "10%", "targets": 4 },
		      { "width": "15%", "targets": 5 },
		      { "width": "15%", "targets": 6 }
		    ]
	});
	
	$('#catTable').dataTable({
		"pageLength": 9,
		"scrollY": 440,
		"columnDefs": [
		      { "width": "45%", "targets": 0 },
		      { "width": "30%", "targets": 1 },
		      { "width": "25%", "targets": 2 }
		    ]
	});
	
	$('#copiesTable').dataTable({
		"pageLength": 10,
		"scrollY": 485,
		"columnDefs": [
		      { "width": "8%", "targets": 0 },
		      { "width": "1%", "targets": 1 },
		      { "width": "27%", "targets": 2 },
		      { "width": "22%", "targets": 3 },
		      { "width": "15%", "targets": 4 },
		      { "width": "10%", "targets": 5 },
		      { "width": "17%", "targets": 6 },
		      { "visible": false, "targets": 1 }
		    ]
	});
	
	$('#subTable').dataTable({
		"pageLength": 8,
		"scrollY": 380,
		"columnDefs": [
		      { "width": "5%", "targets": 0 },
		      { "width": "15%", "targets": 1 },
		      { "width": "35%", "targets": 2 },
		      { "width": "15%", "targets": 3 },
		      { "width": "15%", "targets": 4 },
		      { "width": "15%", "targets": 5 }
		    ]
	});
	
	$('#catDetails').dataTable({
		"pageLength": 10,
		"scrollY": 350,
		"columnDefs": [
		      { "width": "15%", "targets": 0 },
		      { "width": "35%", "targets": 1 },
		      { "width": "35%", "targets": 2 },
		      { "width": "15%", "targets": 3 }
		    ]
	});
	
	$('#authorDetails').dataTable({
		"pageLength": 10,
		"scrollY": 350,
		"columnDefs": [
		      { "width": "20%", "targets": 0 },
		      { "width": "60%", "targets": 1 },
		      { "width": "20%", "targets": 2 }
		    ]
	});
	
	$('#subDetails').dataTable({
		"pageLength": 10,
		"scrollY": 350,
		"columnDefs": [
		      { "width": "5%", "targets": 0 },
		      { "width": "35%", "targets": 1 },
		      { "width": "15%", "targets": 2 },
		      { "width": "15%", "targets": 3 },
		      { "width": "15%", "targets": 4 },
		      { "width": "15%", "targets": 5 }
		    ]
	});
	
	$('[data-toggle="popover"]').popover({
	    'trigger': 'click',
	    'placement': 'top',
	    'container': 'body'
	});
	
	$('body').on('click', function (e) {
	    if ($(e.target).data('toggle') !== 'popover'
	        && $(e.target).parents('.popover.in').length === 0) { 
	        $('[data-toggle="popover"]').popover('hide');
	    }
	 });
	

	$('[data-toggle="tooltip"]').tooltip({
		'trigger' : 'hover',
		'container' : 'body'
	});

	
});