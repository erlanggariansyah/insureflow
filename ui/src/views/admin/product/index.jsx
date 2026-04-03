import React, { useCallback, useRef } from "react";
import ReactFlow, { 
  addEdge, 
  Background, 
  Controls, 
  MiniMap,
  useNodesState,
  useEdgesState,
  Handle,
  Position,
  ReactFlowProvider,
  useReactFlow
} from "reactflow";
import "reactflow/dist/style.css";
import { 
  MdShield, MdGavel, MdPayments, MdTrendingUp, 
  MdSave, MdAutoGraph, MdMoreVert 
} from "react-icons/md";

const EntityNode = ({ data }) => {
  const isProduct = data.type === "product";
  
  return (
    <div className="group min-w-[260px] rounded-2xl border border-gray-200 bg-white pb-2 shadow-xl transition-all hover:border-blue-500 dark:border-navy-700 dark:bg-navy-800">
      <div className={`flex items-center justify-between rounded-t-2xl px-4 py-3 text-white ${data.color}`}>
        <div className="flex items-center gap-2">
          {data.icon}
          <span className="text-xs font-black uppercase tracking-widest">{data.label}</span>
        </div>
        <div className="flex items-center gap-2">
          <span className="rounded-full bg-white/20 px-2 py-0.5 text-[9px] font-bold backdrop-blur-md">
            {data.status || 'ACTIVE'}
          </span>
          <MdMoreVert className="cursor-pointer opacity-70 hover:opacity-100" />
        </div>
      </div>
      
      <div className="px-4 py-3">
        <h4 className="mb-3 text-sm font-bold text-navy-700 dark:text-white">
          {data.name || "Unnamed Entity"}
        </h4>
        <div className="space-y-2">
          {data.configs.map((config, idx) => (
            <div key={idx} className="flex flex-col gap-1 border-b border-gray-50 pb-2 last:border-0 dark:border-navy-700">
              <div className="flex justify-between text-[10px]">
                <span className="font-semibold text-gray-400 uppercase">{config.key}</span>
                <span className="font-mono text-blue-500">{config.value}</span>
              </div>
            </div>
          ))}
        </div>
      </div>

      <div className="mt-2 flex items-center justify-between px-4 py-2 border-t border-gray-50 dark:border-navy-700">
        <button className="text-[10px] font-bold text-blue-600 hover:underline">EDIT DETAILS</button>
        {data.isMandatory && (
          <span className="text-[9px] font-bold text-red-500 uppercase italic">Mandatory</span>
        )}
      </div>

      <Handle type="target" position={Position.Left} className="!h-3 !w-3 !bg-blue-500 border-2 border-white" />
      <Handle type="source" position={Position.Right} className="!h-3 !w-3 !bg-blue-500 border-2 border-white" />
    </div>
  );
};

const nodeTypes = { entity: EntityNode };

const ProductView = () => {
  const reactFlowWrapper = useRef(null);
  const { screenToFlowPosition } = useReactFlow();
  
  const initialNodes = [
    {
      id: "p1",
      type: "entity",
      position: { x: 50, y: 150 },
      data: { 
        type: "product", label: "Base Product", name: "Life Term Plus v2", color: "bg-blue-700",
        configs: [{ key: "Code", value: "LTP-001" }, { key: "Type", value: "TERM_LIFE" }]
      },
    },
    {
      id: "rid1",
      type: "entity",
      position: { x: 450, y: 50 },
      data: { 
        type: "rider", label: "Rider", name: "Accidental Death", color: "bg-purple-600", isMandatory: true,
        configs: [{ key: "Method", value: "LUMP_SUM" }, { key: "Limit", value: "500M" }]
     },
    },
    {
      id: "rule1",
      type: "entity",
      position: { x: 850, y: 50 },
      data: { 
        type: "rule", label: "Rule", name: "Age Eligibility", color: "bg-orange-500",
        configs: [{ key: "Exp", value: "age >= 18" }, { key: "Action", value: "REJECT" }]
      },
    },
    {
      id: "rate1",
      type: "entity",
      position: { x: 850, y: 280 },
      data: { 
        type: "rate", label: "Pricing", name: "Standard Rate", color: "bg-black-600",
        configs: [{ key: "Gender", value: "ALL" }, { key: "Base", value: "1.25" }]
      },
    },
  ];

  const initialEdges = [
    { id: "e1", source: "p1", target: "rid1", animated: true, label: "includes" },
    { id: "e2", source: "rid1", target: "rule1", strokeDasharray: "5,5", label: "validated by" },
    { id: "e3", source: "p1", target: "rate1", label: "pricing" },
  ];

  const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
  const [edges, setEdges, onEdgesChange] = useEdgesState(initialEdges);

  const onConnect = useCallback((params) => setEdges((eds) => addEdge(params, eds)), [setEdges]);

  const onDragStart = (event, nodeType) => {
    event.dataTransfer.setData("application/reactflow", nodeType);
    event.dataTransfer.effectAllowed = "move";
  };

  const onDragOver = useCallback((event) => {
    event.preventDefault();
    event.dataTransfer.dropEffect = "move";
  }, []);

  const onDrop = useCallback(
    (event) => {
      event.preventDefault();
      const type = event.dataTransfer.getData("application/reactflow");
      if (!type) return;

      const position = screenToFlowPosition({ x: event.clientX, y: event.clientY });
      
      let nodeData = { type: type, configs: [{ key: "Status", value: "DRAFT" }] };
      switch (type) {
        case "product": nodeData = { ...nodeData, label: "Product", name: "New Product", color: "bg-blue-700", icon: <MdShield /> }; break;
        case "rider": nodeData = { ...nodeData, label: "Rider", name: "New Rider", color: "bg-purple-600", icon: <MdPayments /> }; break;
        case "rule": nodeData = { ...nodeData, label: "Rule", name: "New Rule", color: "bg-orange-500", icon: <MdGavel /> }; break;
        case "rate": nodeData = { ...nodeData, label: "Pricing", name: "New Rate", color: "bg-emerald-600", icon: <MdTrendingUp /> }; break;
        default: break;
      }

      const newNode = {
        id: `${type}-${Date.now()}`,
        type: "entity",
        position,
        data: nodeData,
      };

      setNodes((nds) => nds.concat(newNode));
    },
    [screenToFlowPosition, setNodes]
  );

  const onAutoLayout = useCallback(() => {
    setNodes((nds) => nds.map((node, i) => ({
      ...node, position: { x: (i % 3) * 400 + 50, y: Math.floor(i / 3) * 300 + 50 }
    })));
  }, [setNodes]);

  const onSaveConfig = useCallback(() => {
    const payload = { nodes, edges };
    console.log("PAYLOAD FOR BACKEND:", JSON.stringify(payload, null, 2));
    alert("Konfigurasi berhasil disimpan! Cek console untuk melihat struktur JSON.");
  }, [nodes, edges]);

  const sidebarItems = [
    { type: "product", label: "Add Product", icon: <MdShield />, color: "text-blue-500", action: "drag" },
    { type: "rider", label: "Add Rider", icon: <MdPayments />, color: "text-purple-500", action: "drag" },
    { type: "rule", label: "Add Rule", icon: <MdGavel />, color: "text-orange-500", action: "drag" },
    { type: "rate", label: "Add Rate", icon: <MdTrendingUp />, color: "text-emerald-500", action: "drag" },
    { type: "layout", label: "Auto-Layout", icon: <MdAutoGraph />, color: "text-emerald-500", action: "click" },
    { type: "save", label: "Save Changes", icon: <MdSave />, color: "text-emerald-500", action: "click" }
  ];

  return (
    <div className="flex h-[calc(100vh-100px)] w-full flex-col gap-4 mt-4">
      <div className="flex flex-grow gap-4 overflow-hidden">
        
        <div className="flex w-64 flex-col gap-4 rounded-2xl bg-white p-4 shadow-sm dark:bg-navy-800">
          <div className="space-y-2">
            {sidebarItems.map((item, i) => (
              <div 
                key={i} 
                draggable={item.action === "drag"}
                onDragStart={(e) => item.action === "drag" && onDragStart(e, item.type)}
                onClick={() => {
                  if (item.type === "layout") onAutoLayout();
                  if (item.type === "save") onSaveConfig();
                }}
                className={`flex items-center gap-3 rounded-xl border border-dashed border-gray-200 p-3 hover:border-blue-500 hover:bg-blue-50/50 dark:border-navy-700 transition-all ${item.action === "drag" ? "cursor-move" : "cursor-pointer"}`}
              >
                <div className={`${item.color}`}>{item.icon}</div>
                <span className="text-[11px] font-bold text-navy-700 dark:text-white">{item.label}</span>
              </div>
            ))}
          </div>
        </div>

        <div 
          className="flex-grow overflow-hidden rounded-2xl border border-gray-100 bg-gray-50 dark:border-transparent dark:bg-navy-900 shadow-inner" 
          ref={reactFlowWrapper}
          onDrop={onDrop}
          onDragOver={onDragOver}
        >
          <ReactFlow
            nodes={nodes}
            edges={edges}
            onNodesChange={onNodesChange}
            onEdgesChange={onEdgesChange}
            onConnect={onConnect}
            nodeTypes={nodeTypes}
            fitView
            deleteKeyCode={["Backspace", "Delete"]}
          >
            <Background variant="dots" gap={20} size={1} color="#cbd5e0" />
            <Controls className="!bg-white !shadow-xl !border-0 dark:!bg-navy-800" />
            <MiniMap className="!bg-white dark:!bg-navy-800 rounded-lg" />
          </ReactFlow>
        </div>
      </div>
    </div>
  );
};

export default function WrappedProductView() {
  return (
    <ReactFlowProvider>
      <ProductView />
    </ReactFlowProvider>
  );
}