import React, { useState } from 'react';
import axios from 'axios'

const AddElement = () => {
    const initialItemCount = parseInt(localStorage.getItem('itemCount') || '0', 10);
    const [itemCount, setItemCount] = useState(initialItemCount);

    const [name, setName] = useState('');
    const [date, setDate] = useState('');
    const [description, setDescription] = useState('');

    const handleSubmit =async (e) => {
        e.preventDefault();

        const data = {
            name: name,
            date: date,
            description: description
        };

        try {
            await axios.post('/jsf2-1.0-SNAPSHOT/api/elements', data); // Utilisez l'URL du endpoint REST
            alert("Element added");
        } catch (error) {
            console.error(error);
        }
        window.location.reload()
        setName('');
        setDate('');
        setDescription('');
    };

    return (
        <div>
            <h1>Ajouter un nouvel élément</h1>
            <form className='forme-add-element' onSubmit={handleSubmit}>
                <div className='field-forme'>
                    <label>Nom:</label>
                    <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
                </div>
                <div className='field-forme'>
                    <label>Date:</label>
                    <input type="date" value={date} onChange={(e) => setDate(e.target.value)} required />
                </div>
                <div className='field-forme'>
                    <label>Description:</label>
                    <textarea
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        required
                    />
                </div>
                <button className='button-add-element' type="submit">Ajouter</button>
            </form>
        </div>
    );
};

export default AddElement;
