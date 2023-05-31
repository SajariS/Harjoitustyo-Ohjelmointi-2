async function removeArtist() {
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const id = urlParams.get("artistId");
	
	await fetch(`?id=${id}`, { method: 'DELETE' });
	
	window.location.replace("/artists")
}

async function removeAlbum() {
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const id = urlParams.get("albumId");
	const artistId = document.getElementById("artistId").value;
	
	await fetch(`?id=${id}`, { method: 'DELETE' });
	
	window.location.replace("/artist?artistId=" + artistId);
	
}

//JS URl parametri luku https://www.sitepoint.com/get-url-parameters-with-javascript/
//JS redirect https://www.w3schools.com/howto/howto_js_redirect_webpage.asp