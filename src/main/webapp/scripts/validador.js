/**
 * Validação do formulário banda
 */
function validar() {
	let nome = frmBanda.nome.value
	let genero = frmBanda.genero.value
	
	if (nome === "") {
		alert('Preencha o campo Nome')
		frmBanda.nome.focus()
		return false
	} else if (genero === "") {
		alert('Preencha o campo Gênero')
		frmBanda.genero.focus()
		return false
	} else {
		document.forms["frmBanda"].submit()
	}
}