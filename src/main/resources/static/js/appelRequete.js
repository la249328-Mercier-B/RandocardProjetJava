async function seConnecter() {
    const pseudo = document.getElementById('pseudo').value.trim();
    const motdepasse = document.getElementById('motdepasse').value.trim();
    const messageErreur = document.getElementById('message-erreur');

    if (!pseudo || !motdepasse) {
        messageErreur.textContent = "Veuillez remplir tous les champs.";
        messageErreur.style.display = 'block';
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/public/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                pseudo: pseudo,
                password: motdepasse
            })
        });

        if (response.ok) {
            const data = await response.json();

            // Sauvegarder le token JWT dans le localStorage
            localStorage.setItem('token', data.token);

            const audio = new Audio('../sounds/carteSound.mp3');
            audio.play();

            audio.onended = () => {
                window.location.href = 'menuPrincipal.html';
            };
        } else {
            const erreur = await response.text();
            messageErreur.textContent = erreur;
            messageErreur.style.display = 'block';
        }

    } catch (erreur) {
        messageErreur.textContent = "Impossible de contacter le serveur.";
        messageErreur.style.display = 'block';
        console.error('Erreur réseau :', erreur);
    }
}

async function sInscrire() {
    const pseudo = document.getElementById('pseudo').value.trim();
    const motdepasse = document.getElementById('motdepasse').value.trim();
    const confirmation = document.getElementById('confirmation').value.trim();
    const messageErreur = document.getElementById('message-erreur');
    const messageSucces = document.getElementById('message-succes');

    // Reset messages
    messageErreur.style.display = 'none';
    messageSucces.style.display = 'none';

    // Vérifications côté client
    if (!pseudo || !motdepasse || !confirmation) {
        messageErreur.textContent = "Veuillez remplir tous les champs.";
        messageErreur.style.display = 'block';
        return;
    }

    if (motdepasse !== confirmation) {
        messageErreur.textContent = "Les mots de passe ne correspondent pas.";
        messageErreur.style.display = 'block';
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/public/inscription', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                pseudo: pseudo,
                password: motdepasse
            })
        });

        if (response.ok) {
            messageSucces.textContent = "Inscription réussie ! Redirection...";
            messageSucces.style.display = 'block';

            const audio = new Audio('../sounds/carteSound.mp3');
            audio.play();

            audio.onended = () => {
                window.location.href = 'index.html';
            };


        } else if (response.status === 409) {
            // Pseudo déjà utilisé (CONFLICT)
            const erreur = await response.text();
            messageErreur.textContent = erreur;
            messageErreur.style.display = 'block';

        } else {
            const erreur = await response.text();
            messageErreur.textContent = erreur || "Erreur lors de l'inscription.";
            messageErreur.style.display = 'block';
        }

    } catch (erreur) {
        messageErreur.textContent = "Impossible de contacter le serveur.";
        messageErreur.style.display = 'block';
        console.error('Erreur réseau :', erreur);
    }
}

document.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') {
        if (document.getElementById('confirmation')) {
            sInscrire();    // Page inscription
        } else {
            seConnecter();  // Page connexion
        }
    }
});



