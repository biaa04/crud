/**
 * Validação do formulário local
 */
 function validarlocal(){
	let endereco = frmLocal.endereco.value
	
	if(endereco === ""){
		alert('Preencha o campo endereço')
		frmLocal.genero.focus()
		return false
	} else{
		document.forms["frmLocal"].submit()
	}
}