import { useEffect } from "react";
import { useParams } from "react-router-dom";
import React from 'react';



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
                <>
                <div className="box">
                    <div className="character-info1">
                        {userData.loaCharacter.armoryProfile.characterName}
                    </div>
                    <div className="box-background">

                        <div className="character-classname">
                            {userData.loaCharacter.armoryProfile.characterClassName}
                        </div>
                        <div className="expedition">
                            {userData.loaCharacter.armoryProfile.expeditionLevel}
                        </div>
                        <div className="character-name">
                            {userData.loaCharacter.armoryProfile.characterName}
                        </div>
                        <div className="character-serverName">
                            {userData.loaCharacter.armoryProfile.serverName}
                        </div>
                        <div className="character-level">
                            Lv. {userData.loaCharacter.armoryProfile.characterLevel}
                        </div>


                        <div className="gem" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '28px',
                            width: '28px',
                        }}>
                            <img
                                src="/gem.png"
                                alt="보석 이미지"
                                style={{width: '25px', height: '25px'}}
                            />
                        </div>
                        <div className="gemName">
                            보석
                        </div>
                        {userData.characterInfo.gem.map((gem, index) => (
                            <React.Fragment key={index}>
                                <div className={`gemValue-${index + 1}`}>
                                    {gem.name + " " + gem.count + "개" + " (평균 : Lv. " + gem.average + ")"} </div>
                            </React.Fragment>
                        ))}


                        <div className="elxir"><img
                            src="/elxir.png"
                            alt="엘릭서 이미지"
                            style={{width: '25px', height: '25px'}}
                        />
                        </div>
                        <div className="elxirName">
                            엘릭서
                        </div>
                        <div className="elxirValue">
                            {userData.characterInfo.elixirName + " " + userData.characterInfo.elixirValue}
                        </div>

                        <div className="cho"><img
                            src="/cho.png"
                            alt="초월 이미지"
                            style={{width: '25px', height: '25px'}}
                        />
                        </div>
                        <div className="choName">
                            초월
                        </div>
                        <div className="choValue">
                            <img
                                src="/cho.png"
                                alt="초월 이미지"
                                style={{width: '15px', height: '15px'}}
                            /> {userData.transcendence.totalValue}
                        </div>

                        <div className="card"><img
                            src="/card.png"
                            alt="카드 이미지"
                            style={{width: '20px', height: '25px'}}
                        />
                        </div>
                        <div className="cardName">
                            카드
                        </div>

                        {userData.characterInfo.cards.map((card, index) => (
                            <React.Fragment key={index}>
                                <div className={`cardValue-${index + 1}`}>
                                    {card.name + " " + card.awakeTotal} </div>
                            </React.Fragment>
                        ))}

                        <div className="synergy"><img
                            src="/synergy.png"
                            alt="시너지 이미지"
                            style={{width: '25px', height: '25px'}}
                        />
                        </div>
                        <div className="synergyName">
                            시너지
                        </div>
                        {userData.characterInfo.synergys.map((stat, index) => (
                            <React.Fragment key={index}>
                                <div className={`synergyValue-${index + 1}`}>
                                    {stat}
                                </div>
                            </React.Fragment>
                        ))}

                        <div className="armor-weapon" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.armorEquipment[0].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>
                        <div className="weapon-quality"
                             style={{
                                 background: `linear-gradient(to right, ${
                                     userData.characterInfo.armorEquipment[0].qualityColor
                                 } ${userData.characterInfo.armorEquipment[0].quality}%, black ${userData.characterInfo.armorEquipment[0].quality}%)`,
                                 width: '50px',
                                 height: '11px',
                             }}
                        >
                            {userData.characterInfo.armorEquipment[0].quality}
                        </div>
                        <div className="weapon-value">
                            {userData.characterInfo.armorEquipment[0].value}강
                            {userData.characterInfo.armorEquipment[0].enhancementValue}
                            <br/><img
                            src="/cho.png"
                            alt="초월 이미지"
                            style={{width: '15px', height: '15px', verticalAlign: 'middle'}}
                        />{userData.characterInfo.armorEquipment[0].transcendenceValue}
                        </div>

                        <div className="armor-helmet" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.armorEquipment[1].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>
                        <div className="helmet-quality"
                             style={{
                                 background: `linear-gradient(to right, ${
                                     userData.characterInfo.armorEquipment[1].qualityColor
                                 } ${userData.characterInfo.armorEquipment[1].quality}%, black ${userData.characterInfo.armorEquipment[1].quality}%)`,
                                 width: '50px',
                                 height: '11px',
                             }}
                        >
                            {userData.characterInfo.armorEquipment[1].quality}
                        </div>
                        <div className="helmet-value">
                            {userData.characterInfo.armorEquipment[1].value}강
                            {userData.characterInfo.armorEquipment[1].enhancementValue}
                            <br/><img
                            src="/cho.png"
                            alt="초월 이미지"
                            style={{width: '15px', height: '15px', verticalAlign: 'middle'}}
                        />{userData.characterInfo.armorEquipment[1].transcendenceValue}
                        </div>

                        <div className="armor-Shoulder" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.armorEquipment[5].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>
                        <div className="shoulder-quality"
                             style={{
                                 background: `linear-gradient(to right, ${
                                     userData.characterInfo.armorEquipment[5].qualityColor
                                 } ${userData.characterInfo.armorEquipment[5].quality}%, black ${userData.characterInfo.armorEquipment[5].quality}%)`,
                                 width: '50px',
                                 height: '11px',
                             }}
                        >
                            {userData.characterInfo.armorEquipment[5].quality}
                        </div>
                        <div className="shoulder-value">
                            {userData.characterInfo.armorEquipment[5].value}강
                            {userData.characterInfo.armorEquipment[5].enhancementValue}
                            <br/><img
                            src="/cho.png"
                            alt="초월 이미지"
                            style={{width: '15px', height: '15px', verticalAlign: 'middle'}}
                        />{userData.characterInfo.armorEquipment[5].transcendenceValue}
                        </div>

                        <div className="armor-top" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.armorEquipment[2].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>
                        <div className="top-quality"
                             style={{
                                 background: `linear-gradient(to right, ${
                                     userData.characterInfo.armorEquipment[2].qualityColor
                                 } ${userData.characterInfo.armorEquipment[2].quality}%, black ${userData.characterInfo.armorEquipment[2].quality}%)`,
                                 width: '50px',
                                 height: '11px',
                             }}
                        >
                            {userData.characterInfo.armorEquipment[2].quality}
                        </div>
                        <div className="top-value">
                            {userData.characterInfo.armorEquipment[2].value}강
                            {userData.characterInfo.armorEquipment[2].enhancementValue}
                            <br/><img
                            src="/cho.png"
                            alt="초월 이미지"
                            style={{width: '15px', height: '15px', verticalAlign: 'middle'}}
                        />{userData.characterInfo.armorEquipment[2].transcendenceValue}
                        </div>

                        <div className="armor-bottom" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.armorEquipment[3].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>
                        <div className="bottom-quality"
                             style={{
                                 background: `linear-gradient(to right, ${
                                     userData.characterInfo.armorEquipment[3].qualityColor
                                 } ${userData.characterInfo.armorEquipment[3].quality}%, black ${userData.characterInfo.armorEquipment[3].quality}%)`,
                                 width: '50px',
                                 height: '11px',
                             }}
                        >
                            {userData.characterInfo.armorEquipment[3].quality}
                        </div>
                        <div className="bottom-value">
                            {userData.characterInfo.armorEquipment[3].value}강
                            {userData.characterInfo.armorEquipment[3].enhancementValue}
                            <br/><img
                            src="/cho.png"
                            alt="초월 이미지"
                            style={{width: '15px', height: '15px', verticalAlign: 'middle'}}
                        />{userData.characterInfo.armorEquipment[3].transcendenceValue}
                        </div>

                        <div className="armor-glove" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.armorEquipment[4].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>
                        <div className="glove-quality"
                             style={{
                                 background: `linear-gradient(to right, ${
                                     userData.characterInfo.armorEquipment[4].qualityColor
                                 } ${userData.characterInfo.armorEquipment[4].quality}%, black ${userData.characterInfo.armorEquipment[4].quality}%)`,
                                 width: '50px',
                                 height: '11px',
                             }}
                        >
                            {userData.characterInfo.armorEquipment[4].quality}
                        </div>
                        <div className="glove-value">
                            {userData.characterInfo.armorEquipment[4].value}강
                            {userData.characterInfo.armorEquipment[4].enhancementValue}
                            <br/><img
                            src="/cho.png"
                            alt="초월 이미지"
                            style={{width: '15px', height: '15px', verticalAlign: 'middle'}}
                        />{userData.characterInfo.armorEquipment[4].transcendenceValue}
                        </div>

                        <div className="accesory-neck" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.accesoryEquipment[0].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>
                        <div className="neck-value">
                            {userData.characterInfo.accesoryEquipment[0].value}+
                            <br/>
                        </div>

                        <div className="accesory-ear1" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.accesoryEquipment[1].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>
                        <div className="ear1-value">
                            {userData.characterInfo.accesoryEquipment[1].value}+
                            <br/>
                        </div>
                        <div className="accesory-ear2" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.accesoryEquipment[2].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>
                        <div className="ear2-value">
                            {userData.characterInfo.accesoryEquipment[2].value}+
                            <br/>
                        </div>
                        <div className="accesory-ring1" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.accesoryEquipment[3].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>
                        <div className="ring1-value">
                            {userData.characterInfo.accesoryEquipment[3].value}+
                            <br/>
                        </div>
                        <div className="accesory-ring2" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.accesoryEquipment[4].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>
                        <div className="ring2-value">
                            {userData.characterInfo.accesoryEquipment[4].value}+
                            <br/>
                        </div>
                        <div className="accesory-bracelet" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.accesoryEquipment[6].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>

                        <div className="accesory-stone" style={{
                            background: 'linear-gradient(135deg, #3d3325, #dcc999)',
                            height: '50px',
                            width: '50px',
                        }}>
                            <img src={userData.characterInfo.accesoryEquipment[5].icon}
                                 style={{width: '50px', height: '50px'}}/>
                        </div>


                        <div className="character-image" style={{
                            backgroundImage: `url(${userData.loaCharacter.armoryProfile.characterImage})`
                        }}>
                        </div>
                        <div className="item-level">
                            {userData.loaCharacter.armoryProfile.itemMaxLevel}
                        </div>

                        {userData.characterInfo.arkPassiveStats.map((stat, index) => (
                            <React.Fragment key={index}>
                                <div className={`ArkPassiveStat-${index + 1}`}>
                                    {stat.value}
                                </div>
                            </React.Fragment>
                        ))}

                        {userData.characterInfo.basicStats.map((basicStats, index) => (
                            <React.Fragment key={index}>
                                <div className={`BasicStatType-${index + 1}`}>
                                    {basicStats.type}
                                </div>
                                <div className={`BasicStatValue-${index + 1}`}>
                                    {basicStats.value}
                                </div>
                            </React.Fragment>
                        ))}

                        {userData.characterInfo.stats.map((stat, index) => (
                            <React.Fragment key={index}>
                                <div className={`StatType-${index + 1}`}>
                                    {stat.type}
                                </div>
                                <div className={`StatValue-${index + 1}`}>
                                    {stat.value}
                                </div>
                            </React.Fragment>
                        ))}

                        <div className="Engraving0">
                            {userData.characterInfo.ownEngraving}
                        </div>
                        {userData.characterInfo.engravings.map((engraving, index) => {
                            const isLegendary = engraving.grade === "전설";
                            const isRelic = engraving.grade === "유물";

                            return (
                                <React.Fragment key={engraving.name}>
                                    <div className={`Engraving${index + 1}`}>
                                        {/* 등급에 따라 색깔과 숫자 추가 */}
                                        {(isLegendary || isRelic) && (
                                            <span
                                                style={{
                                                    display: "inline-block",
                                                    width: "20px",
                                                    height: "20px",
                                                    backgroundColor: isRelic ? "rgb(254,96,0)" : "rgb(233,136,0)",
                                                    color: "black",
                                                    textAlign: "center",
                                                    lineHeight: "20px",
                                                    marginRight: "5px",
                                                }}
                                            >
                        {engraving.level}
                    </span>
                                        )}
                                        <span style={{color: isRelic ? "rgb(254,96,0)" : "rgb(233,136,0)"}}>
                                            {engraving.name}
                                        </span>

                                    </div>
                                </React.Fragment>
                            );
                        })}
                    </div>
                </div>
                    <div className="box2">
                        asd
                    </div>
                </>

            ) : (
                <div className="box">시부레</div>
            )}
        </div>
    );
}
export default Search;