import { useEffect } from "react";
import { useParams } from "react-router-dom";
import React from 'react';



const Search = ({userData, setUserData }) => {

    const { characterName } = useParams();

    useEffect(() => {
        if (!userData && characterName) {
            fetch("http://3.34.71.5:8080/char/" + characterName, {
                method: 'GET',
                headers: {
                    "Content-Type": "application/json",
                },
            })
                .then(res => res.json())
                .then(data => {
                    if(data.flagSuccess){
                        setUserData(data);
                    }else{
                        setUserData(null);
                    }
                    console.log(data);
                })
                .catch(err => console.log(err));
        }
    }, [characterName, userData, setUserData]);

    return (
        <div className="center-container">
            {userData && userData.flagSuccess ? (
                <div className="box">
                    <div className="character-info1">
                        {userData.loaCharacter.armoryProfile.characterName}
                    </div>
                    <div className="box-contents">
                    <div className="first-row">
                        <div className="character-info2">
                            <div className="character-info3">
                                {/* 왼쪽 텍스트 내용 영역 */}
                                <div className="info-content">
                                    <dl>
                                        <dt>서버</dt>
                                        <dd>{userData.loaCharacter.armoryProfile.serverName}</dd>
                                    </dl>
                                    <dl>
                                        <dt>클래스</dt>
                                        <dd>{userData.loaCharacter.armoryProfile.characterClassName}</dd>
                                    </dl>
                                    <dl>
                                        <dt>원정대</dt>
                                        <dd>{userData.loaCharacter.armoryProfile.expeditionLevel}</dd>
                                    </dl>
                                    <dl>
                                        <dt>아이템 레벨</dt>
                                        <dd>{userData.loaCharacter.armoryProfile.itemMaxLevel}</dd>
                                    </dl>
                                    <dl>
                                        <dt>캐릭터 레벨</dt>
                                        <dd>{userData.loaCharacter.armoryProfile.characterLevel}</dd>
                                    </dl>
                                    <dl>
                                        <dt>칭호</dt>
                                        <dd>{userData.loaCharacter.armoryProfile.title}</dd>
                                    </dl>
                                </div>
                                {/* 오른쪽 배경 이미지 영역 */}
                                <div
                                    className="background-image"
                                    style={{
                                        backgroundImage: `url(${userData.loaCharacter.armoryProfile.characterImage})`
                                    }}
                                />
                            </div>
                        </div>
                        <div className="character-info5">
                            <div className="characterInfo">캐릭터 정보</div>
                            <div className="info-details">
                                <div className="info-item">
                                    <div className="characterInfo-stat">특성</div>
                                    <dl>
                                        {userData.characterInfo.stats.map(stat => (
                                            <React.Fragment key={stat.type}>
                                                <dt>{stat.type}</dt>
                                                <dd>{stat.value}</dd>
                                            </React.Fragment>
                                        ))}
                                    </dl>
                                </div>
                                <div className="info-item">
                                    <div className="characterInfo-stat">아크패시브</div>
                                    <dl>
                                        {userData.characterInfo.arkPassiveStats.map((stat,index) => (
                                            <React.Fragment key={index}>
                                                <dt>{stat.name}</dt>
                                                <dd>{stat.value}</dd>
                                            </React.Fragment>
                                        ))}
                                    </dl>
                                </div>
                                <div className="info-item">
                                    <div className="characterInfo-stat">시너지</div>
                                    <dl>
                                        {userData.characterInfo.synergys.map((stat, index) => (
                                            <React.Fragment key={index}>
                                                <dt>{stat}</dt>
                                            </React.Fragment>
                                        ))}
                                    </dl>
                                </div>
                                <div className="info-item">
                                    <div className="characterInfo-stat">정보 1</div>
                                    <dl>
                                        <dt>무기 강화</dt>
                                        <dd>{userData.characterInfo.weaponGrade + " " + userData.characterInfo.weaponValue}</dd>
                                        <dt>스킬 포인트</dt>
                                        <dd>{userData.loaCharacter.armoryProfile.totalSkillPoint}</dd>
                                        <dt>전설 아바타</dt>
                                        <dd>{userData.characterInfo.avatars + " 부위"}</dd>
                                    </dl>

                                </div>
                            </div>
                        </div>
                        <div className="character-info4">
                            <div className="choweol"><img
                                src="/cho.png"
                                alt="초월 이미지"
                                style={{verticalAlign: 'middle'}} // vertical-align 속성 적용
                            />초월
                            </div>
                            <dl>
                                <dt>초월 수치</dt>
                                <dd>{Number(userData.transcendence.weaponValue) + Number(userData.transcendence.armorValue)}</dd>
                            </dl>
                            <dl>
                                <dt>무기 초월</dt>
                                <dd>{userData.transcendence.weaponValue}</dd>
                            </dl>
                            <dl>
                                <dt>방어구 초월</dt>
                                <dd>{userData.transcendence.armorValue}</dd>
                            </dl>
                        </div>
                    </div>
                        <div className="second-row">
                            <div className="character-info6">
                                <div className="elxir"><img
                                    src="/elxir.png"
                                    alt="엘릭서 이미지"
                                    style={{verticalAlign: 'middle', width: '25px', height: '25px'}}
                                /> 엘릭서
                                </div>
                                <dl>
                                    <dd>{userData.characterInfo.elixirName + " " + userData.characterInfo.elixirValue}</dd>
                                </dl>
                                <div className="card"><img
                                    src="/card.png"
                                    alt="카드 이미지"
                                    style={{verticalAlign: 'middle', width: '20px', height: '25px'}}
                                /> 카드
                                </div>
                                <dl>
                                    {userData.characterInfo.cards.map(card => (
                                        <React.Fragment key={card.name}>
                                            <dt>{card.name + card.awakeTotal}</dt>
                                        </React.Fragment>
                                    ))}
                                </dl>
                            </div>
                            <div className="character-info8">
                                <div className="characterInfo">보석</div>
                                <dl>
                                    {Object.entries(userData.characterInfo.gem.map).map(([key, value]) => (
                                        <dt key={key}>
                                            {key}: {value+"개"}
                                        </dt>
                                                                            ))}
                                </dl>
                            </div>
                            <div className="character-info7">
                                <div className="characterInfo">각인</div>
                                <div className="info-detail">
                                    <div className="info-items">
                                        <dl>
                                            {userData.characterInfo.engravings.map(engraving => (
                                                <React.Fragment key={engraving.name}>
                                                    <dt>{engraving.name}</dt>
                                                    {engraving.grade ? (
                                                        <dd>{engraving.grade +" "+engraving.level+"등급"}</dd>
                                                    ) : (
                                                        <dd>{engraving.level}</dd>
                                                    )}
                                                </React.Fragment>
                                            ))}
                                        </dl>
                                    </div>
                                    <div className="info-item-ark">
                                        <dl className="arkPassiveEffects">
                                            {Object.entries(userData.characterInfo.arkPassiveEffects).map(([category, details]) => (
                                                <div key={category}>
                                                    <dt>{category}</dt> {/* 카테고리 이름 출력 */}
                                                    <dl>
                                                        {details.map((item, index) => (
                                                            <dt key={index}>{item}</dt>
                                                        ))}
                                                    </dl>
                                                </div>
                                            ))}
                                        </dl>
                                    </div>
                                </div>
                            </div>
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