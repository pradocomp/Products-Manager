// Function for apllication remove some product
$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var idProduto = button.data('id');
	var codigoProduto = button.data('codigo');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.attr('data-ur-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + idProduto);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o produto <strong>' + codigoProduto + '</strong>?');
});

// Function for aplication tooltip and mask-money
$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: false});
	
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();
		
		var botaoAtualizar = $(event.currentTarget);
		var urlAtualizar = botaoAtualizar.attr('href');
		
		var responde = $.ajax({
			url: urlAtualizar,
			type: 'PUT'
		});
		
		responde.done(function(e) {
			var idProduto = botaoAtualizar.data('id');
			$('[data-role=' + idProduto + ']').html('<span class="label label-success">' + e + '</span>');
		});
		
		responde.fail(function(e) {
			console.log(e);
		});
	});
});

