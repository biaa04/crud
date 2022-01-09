/**
 * confirmação da excluão de uma banda
 */
 
 function confirmar(idbanda){
	let respostabanda = confirm ("Você deseja excluir essa banda?")
	if(respostabanda === true){
		//alert(idbanda)
		window.location.href = "delete?idbanda=" + idbanda
	}
	
}