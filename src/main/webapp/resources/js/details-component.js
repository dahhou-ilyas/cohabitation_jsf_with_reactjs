import React, { useEffect, useState } from 'react';



const DeatilsElements = () => {
    const [id,setId]=useState('')
    const [nom,setNom]=useState('');
    const [description,setDescription]=useState('');
    const [date,setDate]=useState('');

    useEffect(()=>{
        const queryParams = new URLSearchParams(window.location.search);
        setId(queryParams.get('id'))
        setNom(queryParams.get('name'))
        setDescription(queryParams.get('description'))
        setDate(queryParams.get('date'))
    },[])

    return (
        <div>
            <h1>Détails de l'Élément</h1>
            <p className='totalName'><span className='kle'>id:</span> <span className='contentDetals'>{id}</span></p>
            <p className='totalName'><span className='kle'>Nom:</span> <span className='contentDetals'>{nom}</span></p>
            <p className='totalName'><span className='kle'>Description:</span> <span className='contentDetals'>{description}</span></p>
            <p className='totalName'><span className='kle'>Description:</span> <span className='contentDetals'>{date}</span></p>
        </div>
    );
};

export default DeatilsElements;