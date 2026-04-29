export const styles =`
        @import url('https://fonts.googleapis.com/css2?family=Fraunces:ital,wght@0,400;0,600;1,400&family=DM+Sans:wght@300;400;500&display=swap');
        * { box-sizing: border-box; margin: 0; padding: 0; }
        .spot-card { transition: box-shadow 0.15s, transform 0.15s; cursor: pointer; }
        .spot-card:hover { box-shadow: 0 4px 16px rgba(0,0,0,0.10); transform: translateY(-1px); }
        .spot-card.active-card { box-shadow: 0 0 0 2px #1a1a1a; }
        .filter-btn { background: none; border: 1px solid #d5cfc8; border-radius: 20px; padding: 5px 14px; font-family: 'DM Sans', sans-serif; font-size: 13px; cursor: pointer; transition: all 0.15s; }
        .filter-btn:hover { background: #e8e2db; }
        .filter-btn.active-btn { background: #1a1a1a; color: #f2ede8; border-color: #1a1a1a; }
        .stat-card { background: white; border-radius: 12px; padding: 20px 24px; }
        .detail-panel { background: white; border-radius: 14px; padding: 28px; position: sticky; top: 24px; }
        .release-btn { width: 100%; background: #1a1a1a; color: #f2ede8; border: none; border-radius: 8px; padding: 12px; font-family: 'DM Sans', sans-serif; font-size: 13px; font-weight: 500; cursor: pointer; transition: opacity 0.15s; }
        .release-btn:hover { opacity: 0.85; }
`