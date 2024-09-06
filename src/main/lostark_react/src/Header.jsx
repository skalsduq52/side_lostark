import {useState} from "react";
import {Link, useNavigate} from "react-router-dom";

const Header = ({setUserData}) => {

    const [inputValue, setInputValue] = useState('');
    const navigate = useNavigate();

    function getUser(characterName) {
        fetch("http://127.0.0.1:8080/loa/"+ characterName,{
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then(res => res.json())
            .then(data => {
                setUserData(data);
                console.log(data);
                navigate("/loa/"+characterName);
            })
            .catch(err => console.log(err));
    }

    const handleClick = () => {
        getUser(inputValue);
    }
    const handleChange = (event) => {
        setInputValue(event.target.value);
    };

    const onKeyPress = (e) => {
        if (e.key === 'Enter') {
            handleClick();
        }
    }

    return (
        <>
            <header className="top-header">
                <div className="container">
                    <Link to="/">민엽 군장검사</Link>
                    <input className="search-cha" placeholder="캐릭터 명을 입력하세요" value={inputValue} onChange={handleChange}
                           onKeyPress={onKeyPress}></input>
                    <button className="chbutton" onClick={handleClick}>조회s</button>
                </div>
            </header>
            <nav className="top-nav">
                <div className="container">
                    <ul className="navigation">
                        <li>홈</li>
                        <li>골드 정보</li>
                        <li>임시</li>
                        <li>임시2</li>
                    </ul>
                </div>
            </nav>
        </>
    );
}
export default Header;