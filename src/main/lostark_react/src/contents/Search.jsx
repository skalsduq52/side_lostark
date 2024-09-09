import { useEffect } from "react";
import { useParams } from "react-router-dom";



const Search = ({userData, setUserData }) => {

    const { characterName } = useParams();

    useEffect(() => {
        if (!userData && characterName) {
            fetch("http://127.0.0.1:8080/char/" + characterName, {
                method: 'GET',
                headers: {
                    "Content-Type": "application/json",
                },
            })
                .then(res => res.json())
                .then(data => setUserData(data))
                .catch(err => console.log(err));
        }
    }, [characterName, userData, setUserData]);

    return (
        <div className="center-container">
            {userData ? (
                <div className="box">
                    <div className="character-info1">
                        {userData.characterName}
                    </div>
                    <div className="character-info2">
                        <div className="character-info3">
                            <img src={userData.characterImage} alt="캐릭터 이미지" className="detail-image" />
                        </div>
                        <div className="character-info4">
                            <dl>
                                <dt>서버</dt>
                                <dd>{userData.serverName}</dd>
                            </dl>
                            <dl>
                                <dt>클래스</dt>
                                <dd>{userData.characterClassName}</dd>
                            </dl>
                            <dl>
                                <dt>원정대</dt>
                                <dd>{userData.expeditionLevel}</dd>
                            </dl>
                            <dl>
                                <dt>아이템 레벨</dt>
                                <dd>{userData.itemMaxLevel}</dd>
                            </dl>
                            <dl>
                                <dt>캐릭터 레벨</dt>
                                <dd>{userData.characterLevel}</dd>
                            </dl>
                            <dl>
                                <dt>칭호</dt>
                                <dd>{userData.title}</dd>
                            </dl>
                        </div>
                    </div>
                </div>
            ) : (
                <div className="box">시부레</div>
            )}
        </div>
    );
}
export default Search;